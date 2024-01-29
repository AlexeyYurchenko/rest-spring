package com.example.rest.rest.service.impl;

import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.Client;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.repository.DatabaseOrderRepository;
import com.example.rest.rest.repository.OrderSpecification;
import com.example.rest.rest.service.ClientService;
import com.example.rest.rest.service.OrderService;
import com.example.rest.rest.utils.BeanUtils;
import com.example.rest.rest.web.model.OrderFilter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
@Service
@AllArgsConstructor
public class DatabaseOrderService implements OrderService {

    private final DatabaseOrderRepository databaseOrderRepository;

    private final ClientService databaseClientService;

    @Override
    public List<Order> filterBy(OrderFilter filter) {
//        return databaseOrderRepository.getByProduct(filter.getProductName()); SQL Query
        return databaseOrderRepository.findAll(OrderSpecification.withFilter(filter)
                , PageRequest.of(
                        filter.getPageNumber(), filter.getPageSize()
                )).getContent();
    }

    @Override
    public List<Order> findAll() {
        return databaseOrderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return databaseOrderRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format(
                        "Order with ID {0} not found",id)));
    }

    @Override
    public Order save(Order order) {
        Client client = databaseClientService.findById(order.getClient().getId());
        order.setClient(client);
        return databaseOrderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        checkForUpdate(order.getId());
        Client client = databaseClientService.findById(order.getClient().getId());
        Order existedOrder = findById(order.getId());
        BeanUtils.copyNonNullProperties(order,existedOrder);
        existedOrder.setClient(client);
        return databaseOrderRepository.save(existedOrder);
    }

    @Override
    public void deleteById(Long id) {
        databaseOrderRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        databaseOrderRepository.deleteAllById(ids);
    }

    @Override
    public void checkForUpdate(Long orderId) {
        OrderService.super.checkForUpdate(orderId);
    }
}
