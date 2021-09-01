package com.getir.order.model;

import com.getir.order.constant.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";

    @Id
    private long id;
    private long customerId;
    private LocalDateTime orderTime;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal totalOrderPrice;
    private OrderStatus orderStatus;
}
