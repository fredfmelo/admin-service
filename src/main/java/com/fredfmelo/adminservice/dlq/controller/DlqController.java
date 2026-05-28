package com.fredfmelo.adminservice.dlq.controller;

import java.time.OffsetDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fredfmelo.adminservice.dlq.model.QueueType;
import com.fredfmelo.adminservice.dlq.service.DlqService;
import com.fredfmelo.orderservice.api.DlqApi;
import com.fredfmelo.orderservice.model.DlqRetryResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DlqController implements DlqApi {

    private final DlqService dlqService;

    @Override
    public ResponseEntity<DlqRetryResponse> retryDlqMessages(String service) {
        QueueType queueType = QueueType.valueOf(service.toUpperCase());

        int retriedMessages = dlqService.retryMessages(queueType);

        DlqRetryResponse response = new DlqRetryResponse()
                .service(service)
                .retriedMessages(retriedMessages)
                .timestamp(OffsetDateTime.now());

        return ResponseEntity.ok(response);
    }
    
}