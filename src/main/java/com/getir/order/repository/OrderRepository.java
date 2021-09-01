package com.getir.order.repository;

import com.getir.order.constant.OrderAggregation;
import com.getir.order.model.Order;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long>{
    List<Order> findOrdersByOrderTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Order> findOrdersByCustomerId(Long customerId);

    @Aggregation(pipeline = {OrderAggregation.QUERY_GET_ORDER_STATS_BY_CUSTOMER})
    List<Document> getOrderStatisticsByCustomerId(Long customerId);
}
