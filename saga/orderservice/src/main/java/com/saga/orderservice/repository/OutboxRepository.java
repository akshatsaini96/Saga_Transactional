package com.saga.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saga.orderservice.entity.Outbox;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, Integer> {

	List<Outbox> findByIsProcessedFalse();

}
