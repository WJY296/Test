package com.wjy;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时器
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private KafkaTemplate kafkaTemplate;

    //消费消息
    @KafkaListener(topics = "topica", id = "g1")
    public void processMessage(ConsumerRecord<?, ?> record) {
        System.out.println("record:" + record.value());
    }

    //消费消息
    @KafkaListener(topics = "topica", id = "g2")
    public void processMessage1(ConsumerRecord<?, ?> record) {
        System.out.println("AArecord:" + record.value());
    }

}
