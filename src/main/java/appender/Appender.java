package appender;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface Appender {

    Logger logger = Logger.getLogger("MyLog");

    default void append(Handler h, String message){
        logger.addHandler(h);
        logger.log(Level.INFO, message);
        logger.removeHandler(h);
    }

    void append(String... message);

}
