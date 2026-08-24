package com.fredfmelo.adminservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties
public class ServiceConfig {

    private Aws aws;

    @Getter
    @Setter
    public static class Aws {
        private DynamoDb dynamodb;
        private Sns sns;
        private Sqs sqs;
    }

    @Getter
    @Setter
    public static class DynamoDb {
        private String tableName;
    }

    @Getter
    @Setter
    public static class Sns {
        private String orderTopicArn;
    }

    @Getter
    @Setter
    public static class Sqs {
        private String inventoryQueue;
        private String inventoryDlq;
    
        private String notificationQueue;
        private String notificationDlq;
    
        private String orderCompletionQueue;
        private String orderCompletionDlq;
    
        private String orderStateQueue;
        private String orderStateDlq;
    
        private String paymentQueue;
        private String paymentDlq;

        private String ledgerQueue;
        private String ledgerDlq;
    }
    
}