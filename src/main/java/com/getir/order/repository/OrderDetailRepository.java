package com.getir.order.repository;

import com.getir.order.model.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderDetailRepository extends MongoRepository<OrderDetail, Long> {
    List<OrderDetail> findOrderDetailsByOrderId(Long id);
}
