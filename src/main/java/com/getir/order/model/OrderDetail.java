package com.getir.order.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class OrderDetail {

    @Transient
    public static final String SEQUENCE_NAME = "order_detail_sequence";

    @Id
    private long id;
    private long orderId;
    private long bookId;
    private int count;
    private BigDecimal totalPrice;
}
