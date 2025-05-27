package com.saga.orderservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.orderservice.entity.Outbox;
import com.saga.orderservice.entity.PurchaseOrder;
import com.saga.orderservice.repository.OrderRepository;
import com.saga.orderservice.repository.OutboxRepository;

import jakarta.transaction.Transactional;
import saga.commondto.dtoclasses.OrderRequest;
import saga.commondto.event.OrderEvent;
import saga.commondto.event.OrderStatus;
import saga.commondto.event.PaymentStatus;

@Service
public class OrderServiceClass {
	@Autowired
	OrderRepository orderRepo;
//	@Autowired
//	OrderProducerEvent orderProducerEvent;
	@Autowired
	OutboxRepository outboxRepo;
	@Autowired
	ObjectMapper objectMapper;

	@Transactional
	public PurchaseOrder createOrder(OrderRequest orderRequest) {

		PurchaseOrder po = orderRepo.save(convertToDto(orderRequest));
		orderRequest.setOrderId(po.getOrderId());
		Outbox outBox = convertToDtoOutbox(orderRequest, OrderStatus.Order_Created);
		outboxRepo.save(outBox);

		return po;

	}

	public Outbox convertToDtoOutbox(OrderRequest orderRequest, OrderStatus orderStatus) {
		OrderEvent orderEvent = new OrderEvent(orderRequest, orderStatus);
		Outbox outbox = new Outbox();
		outbox.setAggregateId(orderRequest.getOrderId());
		outbox.setCreatedAt(new Date());
		outbox.setProcessed(false);
		try {
			outbox.setPayLoad(objectMapper.writeValueAsString(orderEvent));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		return outbox;

	}

	public PurchaseOrder convertToDto(OrderRequest orderRequest) {

		PurchaseOrder po = new PurchaseOrder();

		po.setAmount(orderRequest.getAmount());
		po.setOrderStatus(OrderStatus.Order_Created);
		po.setPaymentStatus(PaymentStatus.Payment_Pending);
		po.setProductId(orderRequest.getProductId());
		po.setUserId(orderRequest.getUserId());
		return po;
	}

}
