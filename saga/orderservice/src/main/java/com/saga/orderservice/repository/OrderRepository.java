package com.saga.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saga.orderservice.entity.PurchaseOrder;

@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
