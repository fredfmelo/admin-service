package com.fredfmelo.adminservice.idempotency.event;

import java.util.UUID;

public interface IdempotentEvent {
    UUID eventId();
}
