package com.santi.walkietalkie.stream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MessageEmitter {

    private final List<SseEmitter> clients = new CopyOnWriteArrayList<>();

    public SseEmitter addClient() {
        SseEmitter emitter = new SseEmitter(0L); // Sin timeout
        clients.add(emitter);

        emitter.onCompletion(() -> clients.remove(emitter));
        emitter.onTimeout(() -> {
            clients.remove(emitter);
            emitter.complete();
        });
        emitter.onError((e) -> {
            clients.remove(emitter);
            emitter.complete();
        });

        return emitter;
    }

    public void emit(String message) {
        for (SseEmitter emitter : clients) {
            try {
                emitter.send(message, MediaType.TEXT_PLAIN);
            } catch (IOException e) {
                emitter.complete();
                clients.remove(emitter);
            }
        }
    }
}
