package com.convector.hbaseloader.output;

import com.convector.hbaseloader.transform.SingleRow;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.sql.Timestamp;
import java.util.Properties;
import java.util.TimerTask;

/**
 * Created by pyro_ on 29/06/2016.
 */
public class TimedPrinter extends TimerTask {

    private static final Properties KAFKA_CONFIG;
    private static final String TOPIC = "validations";

    private static KafkaProducer<String,String> producer;
    private String rowkey;
    private String json;

    static {
        KAFKA_CONFIG = new Properties();
        KAFKA_CONFIG.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "slave1:9092");
        //KAFKA_CONFIG.put("group.id","convector");
        KAFKA_CONFIG.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KAFKA_CONFIG.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(KAFKA_CONFIG);
    }

    public TimedPrinter(String rowkey, String json){
        this.rowkey = rowkey;
        this.json = json;
    }

    @Override
    public void run() {
        this.producer.send(new ProducerRecord<>(TOPIC,rowkey,json));
        System.out.println(new Timestamp(System.currentTimeMillis()).toString() + "|||" + json);
    }
}
