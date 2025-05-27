package com.saga.orderservice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "outbox_events")
@Data
public class Outbox {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "aggregate_id")
	private int aggregateId;

	@Column(name = "pay_load", columnDefinition = "TEXT")
	private String payLoad;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "is_processed")
	private boolean isProcessed;
}
