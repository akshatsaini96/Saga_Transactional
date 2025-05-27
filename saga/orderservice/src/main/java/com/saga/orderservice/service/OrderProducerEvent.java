package com.saga.orderservice.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderProducerEvent {

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	@Value("${outbox.topic.name}")
	private String topic;

	public void publishOrderEvent(String orderEvent) {

		int attempt = 1;
		int maxAttempt = 3;
		int backOff = 2000;
		while (attempt <= maxAttempt) {
			try {
				SendResult<String, Object> result = kafkaTemplate.send(topic, orderEvent).get();
				log.info("msg published successfully--" + result.getRecordMetadata().partition() + "msg is --"
						+ orderEvent);
				return;
			} catch (InterruptedException | ExecutionException e) {

				log.info("error is due to" + e.getMessage());
				log.info("Retrying attempt" + attempt);
				attempt++;
			}

			if (attempt == maxAttempt) {
				log.info("retries exhausted adding to dlq");
				throw new RuntimeException();
			}
			try {

				Thread.sleep(backOff);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
