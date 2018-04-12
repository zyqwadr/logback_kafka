package xyz.util;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KafkaUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaUtils.class);

    private static Producer<String, String> producer;

    private KafkaUtils() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafka.server.com:9092");//服务器ip:端口号，集群用逗号分隔 zookeeper.server.com:2181  kafka.server.com:9092
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String,String>(props);
    }



    /**
     * 发送对象消息 至kafka上,调用json转化为json字符串，应为kafka存储的是String。 
     * @param msg
     */
    public void sendMsgToKafka(String msg) {
        producer.send(new ProducerRecord<String, String>("TEST_LOG", String.valueOf(new Date().getTime()), msg));
    }

    public void closeKafkaProducer() {
        producer.close();
    }

//    public static void main(String[] args) {
//        new xyz.util.KafkaUtils().sendMsgToKafka("1111");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}