package com.getir.order.service;

import com.getir.order.dto.OrderDTO;
import com.getir.order.model.Order;

import java.util.List;

public interface OrderService {
    OrderDTO saveNewOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<Order> getOrderListByDateRange(String startDate, String endDate);
    List<Order> getOrderListByCustomer(Long id);
    List<OrderDTO> getOrderDTOList(List<Order> orderList);
}
