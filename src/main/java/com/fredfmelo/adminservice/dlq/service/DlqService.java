package com.fredfmelo.adminservice.dlq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fredfmelo.adminservice.config.ServiceConfig;
import com.fredfmelo.adminservice.dlq.model.QueueType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class DlqService {

    private final SqsClient sqsClient;
    private final ServiceConfig serviceConfig;

    public int retryMessages(QueueType queueType) {

        String dlqUrl = getDlqUrl(queueType);
        String queueUrl = getQueueName(queueType);

        List<Message> messages = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl(dlqUrl)
                .maxNumberOfMessages(10)
                .build())
                .messages();

        if (!messages.isEmpty()) {
            log.info("Retrying {} messages from {}", messages.size(), dlqUrl);

            for (Message message : messages) {
                retryMessage(queueUrl, message);
                deleteMessage(dlqUrl, message);
            }
        }

        return messages.size();
    }

    private void retryMessage(String queueUrl, Message message) {

        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message.body())
                .build());
    }

    private void deleteMessage(String dlqUrl, Message message) {

        sqsClient.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl(dlqUrl)
                .receiptHandle(message.receiptHandle())
                .build());
    }

    private String getQueueName(QueueType queueType) {

        return switch (queueType) {

            case INVENTORY ->
                serviceConfig.getAws().getSqs().getInventoryQueue();

            case NOTIFICATION ->
                serviceConfig.getAws().getSqs().getNotificationQueue();

            case ORDER_COMPLETION ->
                serviceConfig.getAws().getSqs().getOrderCompletionQueue();

            case ORDER_STATE ->
                serviceConfig.getAws().getSqs().getOrderStateQueue();

            case PAYMENT ->
                serviceConfig.getAws().getSqs().getPaymentQueue();
        };
    }

    private String getDlqUrl(QueueType queueType) {

        return switch (queueType) {

            case INVENTORY ->
                serviceConfig.getAws().getSqs().getInventoryDlq();

            case NOTIFICATION ->
                serviceConfig.getAws().getSqs().getNotificationDlq();

            case ORDER_COMPLETION ->
                serviceConfig.getAws().getSqs().getOrderCompletionDlq();

            case ORDER_STATE ->
                serviceConfig.getAws().getSqs().getOrderStateDlq();

            case PAYMENT ->
                serviceConfig.getAws().getSqs().getPaymentDlq();
        };
    }
}