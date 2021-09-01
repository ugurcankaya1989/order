package com.getir.order.controller;

import com.getir.order.dto.OrderDTO;
import com.getir.order.exception.OrderNotFoundByIdException;
import com.getir.order.model.Order;
import com.getir.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<OrderDTO> saveNewOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.saveNewOrder(orderDTO));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/get-by-date")
    public ResponseEntity<List<OrderDTO>> getOrderListByDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        List<Order> orderList = orderService.getOrderListByDateRange(startDate, endDate);
        if (CollectionUtils.isEmpty(orderList))
            throw new OrderNotFoundByIdException();
        return ResponseEntity.ok(orderService.getOrderDTOList(orderList));
    }

    @GetMapping("/get-by-customer")
    public ResponseEntity<List<OrderDTO>> getOrderListByCustomerId(@RequestParam("id") Long id) {
        List<Order> orderList = orderService.getOrderListByCustomer(id);
        if (CollectionUtils.isEmpty(orderList))
            throw new OrderNotFoundByIdException();
        return ResponseEntity.ok(orderService.getOrderDTOList(orderList));
    }

}
