package com.getir.order.adapter;

import com.getir.order.dto.OrderDTO;
import com.getir.order.dto.OrderDetailDTO;
import com.getir.order.model.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderAdapter {

    public OrderDTO prepareOrderDto(Order order, List<OrderDetailDTO> orderDetailDTOList) {
        OrderDTO orderDTO = OrderDTO.builder().build();
        BeanUtils.copyProperties(order, orderDTO);
        orderDTO.setOrderDetailDTOList(orderDetailDTOList);
        return orderDTO;
    }

}
