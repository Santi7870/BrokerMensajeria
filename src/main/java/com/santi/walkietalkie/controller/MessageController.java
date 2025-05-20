package com.santi.walkietalkie.controller;

import com.santi.walkietalkie.stream.MessageEmitter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageEmitter messageEmitter;

    public MessageController(MessageEmitter messageEmitter) {
        this.messageEmitter = messageEmitter;
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamMessages() {
        return messageEmitter.addClient();
    }

    @PostMapping("/send")
    public String receiveMessage(@RequestBody MessageRequest incomingMessage) {
        String fullMessage = incomingMessage.alias + ": " + LocalDateTime.now() + " - " + incomingMessage.message;
        messageEmitter.emit(fullMessage);
        return "Mensaje recibido y enviado a clientes conectados";
    }
}

