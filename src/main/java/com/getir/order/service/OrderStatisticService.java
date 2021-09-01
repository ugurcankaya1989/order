package com.getir.order.service;

import com.getir.order.dto.OrderStatisticDTO;
import com.getir.order.model.OrderStatistic;

import java.util.List;

public interface OrderStatisticService {
    List<OrderStatisticDTO> getStatsByCustomerId(Long customerId);
}
