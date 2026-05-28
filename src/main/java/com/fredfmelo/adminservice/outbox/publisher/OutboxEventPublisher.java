package com.fredfmelo.adminservice.outbox.publisher;

public interface OutboxEventPublisher {

    void publish(String payload, String eventType);
}