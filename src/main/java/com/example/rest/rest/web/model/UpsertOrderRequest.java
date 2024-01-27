package com.example.rest.rest.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Data
public class UpsertOrderRequest {

    @NotNull(message = "Id not be null")
    @Positive(message = "ID must be greater than 0")
    private Long clientId;

    @NotBlank(message = "Product name must be specified")
    private String product;

    @NotNull(message = "the price must be indicated")
    @Positive(message = "Cost must be greater than 0")
    private BigDecimal cost;
}
