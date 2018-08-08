package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4j2Test {
    @Test
    public void testLog4j2() {
        Logger logger = LogManager.getLogger(Log4j2Test.class.getName());
        for (int i = 0; i < 10000; i++) {
            logger.info("infor");
            logger.error("error");
            logger.warn("warn");
        }

    }

}
