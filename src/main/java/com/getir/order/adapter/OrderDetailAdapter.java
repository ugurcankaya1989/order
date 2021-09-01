package com.getir.order.adapter;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.getir.order.dto.OrderDetailDTO;
import com.getir.order.model.Order;
import com.getir.order.model.OrderDetail;
import com.getir.order.service.SequenceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDetailAdapter {

    private final SequenceService sequenceService;

    public OrderDetailAdapter(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public List<OrderDetail> prepareOrderDetailList(Order order, List<OrderDetailDTO> orderDetailDTOList) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(orderDetailDTO,orderDetail);
            orderDetail.setOrderId(order.getId());
            orderDetail.setId(sequenceService.getSequenceNumber(OrderDetail.SEQUENCE_NAME));
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }

    public List<OrderDetailDTO> prepareOrderDetailDtoList(List<OrderDetail> orderDetails) {
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (OrderDetail od : orderDetails) {
            OrderDetailDTO orderDetailDTO = OrderDetailDTO.builder().build();
            BeanUtils.copyProperties(od, orderDetailDTO);
            orderDetailDTOList.add(orderDetailDTO);
        }
        return orderDetailDTOList;
    }


}
