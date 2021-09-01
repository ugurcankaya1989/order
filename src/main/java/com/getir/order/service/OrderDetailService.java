package com.getir.order.service;

import com.getir.order.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> saveAllOrderDetails(List<OrderDetail> orderDetailList);
    List<OrderDetail> findOrderDetailListByOrderId(Long id);
}
