import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class runApp {


    public static void main(String[] args) {
//        LoggingTest loggingTest = new LoggingTest();
//        loggingTest.testLogging();

        Logger logger = LoggerFactory.getLogger(runApp.class);
        try {
            int i = 2 / 0;
        } catch (Exception e){
            Map returnMap = new HashMap();
            returnMap.put("1","test");
            returnMap.put("2","test2");
            logger.info("test = {}", returnMap,e);
//            logger.info("test = {}", e.getMessage());

        }
    }
}
