package com.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbit.config.MessagingConfig;
import com.rabbit.dto.OrderStatus;

@Component
public class User {
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus status) {
		System.out.println("message has been recieved from queue " + status);
	}
}
