package com.example.rest.rest.web.controller.v2;

import com.example.rest.rest.mapper.v2.OrderMapperV2;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.service.impl.DatabaseOrderService;
import com.example.rest.rest.web.model.OrderListResponse;
import com.example.rest.rest.web.model.OrderResponse;
import com.example.rest.rest.web.model.UpsertOrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/order")
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final DatabaseOrderService databaseOrderService;
    private final OrderMapperV2 orderMapperV2;

    @GetMapping
    public ResponseEntity<OrderListResponse> findAll() {
        return ResponseEntity.ok(
                orderMapperV2.orderListToOrderListResponse(databaseOrderService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                orderMapperV2.orderToResponse(databaseOrderService.findById(id)));
    }
    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid UpsertOrderRequest request){
        Order newOrder = databaseOrderService.save(orderMapperV2.requestToOrder(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapperV2.orderToResponse(newOrder));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable("id")Long orderId
            ,@RequestBody @Valid UpsertOrderRequest request){
        Order updateOrder = databaseOrderService.update(orderMapperV2.requestToOrder(orderId,request));
        return ResponseEntity.ok(orderMapperV2.orderToResponse(updateOrder));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        databaseOrderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
