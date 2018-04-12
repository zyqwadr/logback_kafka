 - logback集成kafka


 - 只需扩展LogbackAppe nder

```
/**
 * 自定义KafkaAppender将日志输出到Kafka中
 * @author liuyazhuang
 *
 */
public class KafkaAppender extends AppenderBase<ILoggingEvent> {

    private Formatter formatter;
    private static Producer<String, String> producer;

    private String topic;
    private String server;

    @Override
    public void start() {
        if(this.formatter == null){
            this.formatter = new JsonFormatter();
        }
        super.start();
        Properties props = new Properties();
        props.put("bootstrap.servers", server);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String,String>(props);
        producer.send(new ProducerRecord<String, String>(topic, ""));
    }

    @Override
    public void stop() {
        super.stop();
        producer.close();
    }
    @Override
    protected void append(ILoggingEvent event) {
        String payload = formatter.format(event);
        producer.send(new ProducerRecord<String, String>(topic, String.valueOf(new Date().getTime()), payload));
    }


    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public static Producer<String, String> getProducer() {
        return producer;
    }

    public static void setProducer(Producer<String, String> producer) {
        KafkaAppender.producer = producer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
```