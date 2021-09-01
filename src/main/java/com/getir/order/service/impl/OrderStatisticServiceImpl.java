package com.getir.order.service.impl;

import com.getir.order.dto.OrderStatisticDTO;
import com.getir.order.service.OrderStatisticService;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OrderStatisticServiceImpl implements OrderStatisticService {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<OrderStatisticDTO> getStatsByCustomerId(Long customerId) {
        List<OrderStatisticDTO> orderStatisticList = new ArrayList<>();
        try  {
            MongoDatabase database = mongoTemplate.getDb();
            MongoCollection<Document> collection = database.getCollection("orders");

            Consumer<Document> processBlock = new Consumer<Document>() {
                @Override
                public void accept(Document document) {
                    orderStatisticList.add(OrderStatisticDTO.builder().month(Month.of(document.getInteger("_id")).name())
                            .totalOrderCount(document.getInteger("totalOrderCount").longValue())
                            .totalBookCount(document.getInteger("totalBookCount").longValue())
                            .totalPurchasedAmount(new BigDecimal(document.get("totalPurchasedAmount").toString())).build());
                }
            };

            List<? extends Bson> pipeline = Arrays.asList(
                    new Document()
                            .append("$project", new Document()
                                    .append("_id", 0)
                                    .append("orders", "$$ROOT")
                            ),
                    new Document()
                            .append("$lookup", new Document()
                                    .append("localField", "orders._id")
                                    .append("from", "order_details")
                                    .append("foreignField", "orderId")
                                    .append("as", "order_details")
                            ),
                    new Document()
                            .append("$unwind", new Document()
                                    .append("path", "$order_details")
                                    .append("preserveNullAndEmptyArrays", false)
                            ),
                    new Document()
                            .append("$match", new Document()
                                    .append("orders.customerId", customerId)
                            ),
                    new Document()
                            .append("$group", new Document()
                                    .append("_id", new Document()
                                            .append("$month", "$orders.orderTime")
                                    )
                                    .append("uniqueCount", new Document()
                                            .append("$addToSet", "$orders._id")
                                    )
                                    .append("SUM(order_details\u1390count)", new Document()
                                            .append("$sum", "$order_details.count")
                                    )
                                    .append("SUM(orders\u1390totalOrderPrice)", new Document()
                                            .append("$sum", "$orders.totalOrderPrice")
                                    )
                            ),
                    new Document()
                            .append("$project", new Document()
                                    .append("_id", 1)
                                    .append("totalOrderCount", new Document().append("$size", "$uniqueCount"))
                                    .append("totalBookCount", "$SUM(order_details\u1390count)")
                                    .append("totalPurchasedAmount", "$SUM(orders\u1390totalOrderPrice)")
                            ),
                    new Document().append("$sort",new Document().append("_id",1))
            );

            collection.aggregate(pipeline)
                    .allowDiskUse(true)
                    .forEach(processBlock);

        } catch (MongoException e) {
            // handle MongoDB exception
        }
        return orderStatisticList;

    }
}
