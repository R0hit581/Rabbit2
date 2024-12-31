package com.rabbit.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.config.MessagingConfig;
import com.rabbit.dto.Order;
import com.rabbit.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
	@Autowired
	private RabbitTemplate template;
	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order,@PathVariable String restaurantName) {
		order.setOrderId(UUID.randomUUID().toString());
		//RESTAURANT SERVICE
		//PAYMENT SERVICE
		OrderStatus orderStatus=new OrderStatus(order,"process","orderplaced susseffully"+restaurantName);
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTINGKEY, orderStatus);
		return "Success";
	}
}
