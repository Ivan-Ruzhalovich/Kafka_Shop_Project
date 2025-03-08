package com.example.ivan.ruzhalovich.testconsumer.kafka;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Retryable(retryFor = {Exception.class},
            maxAttempts = 10,
            backoff = @Backoff(value = 1000))
    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "Logs", partitions = {"0", "1", "2", "3"}),
            groupId = "test_consumer",
            concurrency = "4")
    public void LogsConsumer(ConsumerRecord<String,String> message){
            log.info("Message:{} was received, partition = {}",message.value(),message.partition());
    }
}
