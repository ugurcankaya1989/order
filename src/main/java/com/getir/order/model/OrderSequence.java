package com.getir.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSequence {
    @Id
    private String  id;
    private int seq;
}
