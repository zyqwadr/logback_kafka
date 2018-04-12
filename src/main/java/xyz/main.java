package xyz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 打开kafka消费者
 * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic TEST_LOG --from-beginning
 */
public class main {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(main.class);
        logger.debug("11");
        logger.debug("11");
        logger.debug("11");
        logger.debug("11");
        logger.debug("11");
        logger.debug("11");
        logger.debug("11");
        new Thread(new Runnable() {
            public void run() {
                logger.debug("13231");logger.debug("32");logger.debug("232");logger.debug("232");
            }
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
