package com.saga.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saga.orderservice.entity.PurchaseOrder;
import com.saga.orderservice.service.OrderServiceClass;

import saga.commondto.dtoclasses.OrderRequest;

@RestController
public class OrderController {
	@Autowired
	OrderServiceClass orderService;

	@PostMapping("/create")
	public PurchaseOrder orderReceived(@RequestBody OrderRequest orderRequest) {

		return orderService.createOrder(orderRequest);

	}

}
