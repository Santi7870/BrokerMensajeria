package com.santi.walkietalkie.controller;

public class MessageRequest {
    public String alias;
    public String message;

    // Constructor vacío para deserialización JSON
    public MessageRequest() {}

    // Opcional: constructor con parámetros
    public MessageRequest(String alias, String message) {
        this.alias = alias;
        this.message = message;
    }

    // Getters y setters (opcional, si usas frameworks que los requieran)
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
