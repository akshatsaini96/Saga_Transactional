package com.saga.paymentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_table")
public class UserTable {
	@Id
	@Column(name = "user_id")
	private int userId;
	@Column(name = "amount")
	private int balance;
}
