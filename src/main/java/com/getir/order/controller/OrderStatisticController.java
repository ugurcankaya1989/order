package com.getir.order.controller;

import com.getir.order.dto.OrderStatisticDTO;
import com.getir.order.service.OrderStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-statistic")
@RequiredArgsConstructor
public class OrderStatisticController {
    private final OrderStatisticService orderStatisticService;

    @GetMapping("/get-order-stats-by-customer/{customerId}")
    public ResponseEntity<List<OrderStatisticDTO>> getOrderStatsByCustomerId(@PathVariable("customerId") Long customerId){
        return ResponseEntity.ok(orderStatisticService.getStatsByCustomerId(customerId));
    }
}
