package com.saga.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.paymentservice.entity.UserTable;

public interface UserBalanceRepo extends JpaRepository<UserTable, Integer> {

}
