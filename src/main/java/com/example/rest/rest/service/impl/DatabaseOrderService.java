package com.example.rest.rest.service.impl;

import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.Client;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.repository.DatabaseOrderRepository;
import com.example.rest.rest.service.ClientService;
import com.example.rest.rest.service.OrderService;
import com.example.rest.rest.utils.BeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
@Service
@AllArgsConstructor
public class DatabaseOrderService implements OrderService {

    private final DatabaseOrderRepository orderRepository;

    private final ClientService databaseClientService;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format(
                        "Order with ID {0} not found",id)));
    }

    @Override
    public Order save(Order order) {
        Client client = databaseClientService.findById(order.getClient().getId());
        order.setClient(client);
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        checkForUpdate(order.getId());
        Client client = databaseClientService.findById(order.getClient().getId());
        Order existedOrder = findById(order.getId());
        BeanUtils.copyNonNullProperties(order,existedOrder);
        existedOrder.setClient(client);
        return orderRepository.save(existedOrder);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        orderRepository.deleteAllById(ids);
    }
}
