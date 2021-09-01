package com.getir.order.dto;

import com.getir.order.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private long id;
    private long customerId;
    private LocalDateTime orderTime;
    private OrderStatus orderStatus;
    private BigDecimal totalOrderPrice;
    private List<OrderDetailDTO> orderDetailDTOList;
}
