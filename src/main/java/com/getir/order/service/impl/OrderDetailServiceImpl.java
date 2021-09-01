package com.getir.order.service.impl;

import com.getir.order.model.OrderDetail;
import com.getir.order.repository.OrderDetailRepository;
import com.getir.order.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderDetail> saveAllOrderDetails(List<OrderDetail> orderDetailList) {
        return orderDetailRepository.saveAll(orderDetailList);
    }

    @Override
    public List<OrderDetail> findOrderDetailListByOrderId(Long id) {
        return orderDetailRepository.findOrderDetailsByOrderId(id);
    }
}
