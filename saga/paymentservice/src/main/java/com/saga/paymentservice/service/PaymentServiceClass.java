package com.saga.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import saga.commondto.dtoclasses.OrderRequest;
import saga.commondto.event.OrderEvent;

@Service
public class PaymentServiceClass {
	@Autowired
	ObjectMapper objectMapper;

	@KafkaListener(topics = "orderEvent", groupId = "orderprocessor")
	public void getOrderEvent(String msg) {
		OrderEvent orderEvent = null;
		try {
			orderEvent = objectMapper.readValue(msg, OrderEvent.class);
			System.out.println(orderEvent.toString());
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		OrderRequest orderRequestDto = orderEvent.getOrderRequestDto();

	}
}
