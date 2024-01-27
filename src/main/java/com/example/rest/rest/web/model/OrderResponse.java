package com.example.rest.rest.web.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OrderResponse {

    private Long id;
    private String product;
    private BigDecimal cost;
}
