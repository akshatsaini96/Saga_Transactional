package com.saga.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.paymentservice.entity.UserTransaction;

public interface UserTransactionRepo extends JpaRepository<UserTransaction, Integer> {

}
