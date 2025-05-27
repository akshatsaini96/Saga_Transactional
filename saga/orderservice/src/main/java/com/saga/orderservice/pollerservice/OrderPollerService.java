package com.saga.orderservice.pollerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.saga.orderservice.entity.Outbox;
import com.saga.orderservice.repository.OutboxRepository;
import com.saga.orderservice.service.OrderProducerEvent;

@Service
@EnableScheduling
public class OrderPollerService {
	@Autowired
	OutboxRepository outboxRepo;
	@Autowired
	OrderProducerEvent kafkaProducer;

	@Scheduled(fixedRate = 60000)
	public void pollOutPublish() {

		List<Outbox> unprocessedMsgs = outboxRepo.findByIsProcessedFalse();
		unprocessedMsgs.forEach(msg -> {
			kafkaProducer.publishOrderEvent(msg.getPayLoad());
			msg.setProcessed(true);
			outboxRepo.save(msg);

		});

	}

}
