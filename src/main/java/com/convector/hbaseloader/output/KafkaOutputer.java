package com.convector.hbaseloader.output;

import com.convector.hbaseloader.transform.SingleRow;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Observable;
import java.util.Properties;

/**
 * Created by pyro_ on 14/06/2016.
 */
public class KafkaOutputer extends Outputer {

    private static final Properties KAFKA_CONFIG;
    private static final String TOPIC = "validations";

    private long startTime;

    private SingleRow currentRow;

    static {
        KAFKA_CONFIG = new Properties();
        KAFKA_CONFIG.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "slave1:9092");
        //KAFKA_CONFIG.put("group.id","convector");
        KAFKA_CONFIG.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KAFKA_CONFIG.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    }

    public KafkaProducer<String, String> producer;

    public KafkaOutputer() {
        this.producer = new KafkaProducer<>(KAFKA_CONFIG);
        startTime = System.currentTimeMillis();
    }

    public void update(Observable o, Object arg) {
        //ProducerRecord<String, String> message = new ProducerRecord<String, String>(TOPIC, String.valueOf(tweet.getId()), json.toString());
        currentRow = (SingleRow) o;
        this.producer.send(new ProducerRecord<>(TOPIC,currentRow.getRowKey(),currentRow.toJSON().toString()));
        ProgressBarPrinter.update((double) arg,System.currentTimeMillis()-startTime);
    }
}
