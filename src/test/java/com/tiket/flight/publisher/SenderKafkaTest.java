package com.tiket.flight.publisher;

import com.tiket.flight.publisher.producers.Sender;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import static java.util.Collections.singleton;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@EmbeddedKafka
public class SenderKafkaTest {
//    @Autowired
    private Sender sender;

    //    @Autowired
    private Consumer<String, String> consumer;

//    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

//    @Before
    public void setUp() {
        Map<String, Object> configs = new HashMap<String, Object>(KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker));
        consumer = new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new StringDeserializer()).createConsumer();
        consumer.subscribe(singleton("test"));
        consumer.poll(Duration.ofSeconds(0));

    }

//    @After
    public void tearDown() {
        consumer.close();
    }


    //    @Test
    public void sendKafkaTest() {
        // Act
//        sender.send("test", "{\"event\":\"Test Event\"}");
//        sender.flush();

        // Assert
        ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, "test");
        assertThat(singleRecord).isNotNull();
//        assertThat(singleRecord.key()).isEqualTo("my-aggregate-id");
//        assertThat(singleRecord.value()).isEqualTo("{\"event\":\"Test Event\"}");

    }
}
