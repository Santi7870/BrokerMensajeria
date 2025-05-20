package com.santi.walkietalkie.consumer;

import com.santi.walkietalkie.stream.MessageEmitter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final MessageEmitter messageEmitter;

    public MessageConsumer(MessageEmitter messageEmitter) {
        this.messageEmitter = messageEmitter;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido desde RabbitMQ: " + message);
        messageEmitter.emit("RabbitMQ: " + message);
    }
}

