package com.example.rest.rest.web.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ClientResponse {
    private Long id;
    private String name;
    private List<OrderResponse> orders = new ArrayList<>();

}
