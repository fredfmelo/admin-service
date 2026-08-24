package com.fredfmelo.adminservice.dlq.model;

public enum QueueType {

    INVENTORY,
    LEDGER,
    NOTIFICATION,
    ORDER_COMPLETION,
    ORDER_STATE,
    PAYMENT
}