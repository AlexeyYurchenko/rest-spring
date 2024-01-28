package com.example.rest.rest.repository;

import com.example.rest.rest.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatabaseOrderRepository extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {

    Page<Order> findAllByProduct(String product, Pageable pageable);

}
