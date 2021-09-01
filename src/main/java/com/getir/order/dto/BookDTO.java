package com.getir.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private long id;
    private String name;
    private String author;
    private BigDecimal unitPrice;
    private long stockCount;
}
