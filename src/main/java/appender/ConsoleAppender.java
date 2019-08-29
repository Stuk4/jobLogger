package appender;

import java.util.logging.ConsoleHandler;

public class ConsoleAppender implements Appender{

    @Override
    public void append(String... message) {
        ConsoleHandler ch = new ConsoleHandler();
        append(ch, message[0]);
    }

}
