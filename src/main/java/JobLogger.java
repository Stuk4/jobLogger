import appender.Appender;
import appender.ConsoleAppender;
import appender.DataBaseAppender;
import appender.FileAppender;
import util.ConnectionUtil;
import util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class JobLogger {

    private boolean logToFile;
    private boolean logToConsole;
    private boolean logMessage;
    private boolean logWarning;
    private boolean logError;
    private boolean logToDatabase;
    private Map dbParams;
    private static Logger logger;

    private void loadEnvironment() throws Exception {
        try (InputStream input = ConnectionUtil.class.getClassLoader()
                .getResourceAsStream("joblogger.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                return;
            }
            prop.load(input);
            logToFile =  StringUtil.booleanFromString(prop.getProperty("logToFile"));
            logToConsole = StringUtil.booleanFromString(prop.getProperty("logToConsole"));
            logToDatabase = StringUtil.booleanFromString(prop.getProperty("logToDatabase"));
            logWarning = StringUtil.booleanFromString(prop.getProperty("logWarning"));
            logError = StringUtil.booleanFromString(prop.getProperty("logError"));
            logMessage = StringUtil.booleanFromString(prop.getProperty("logMessage"));
            dbParams = prop;
        } catch (IOException ex) {
            throw new Exception("Error loading environment properties");
        }

    }


    public JobLogger() throws Exception {
        loadEnvironment();
    }

    public void logMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {

        messageText.trim();
        if(StringUtil.isEmpty(messageText)){
            return;
        }

        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }
        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
            throw new Exception("Error or Warning or Message must be specified");
        }

        List<LogMessage> messageList = new ArrayList<>();

        if (error && logError) {
            messageList.add(new LogMessage(LogLevel.ERROR.getLogTemplate()+" "+messageText, LogLevel.ERROR.level));
        }

        if (warning && logWarning) {
            messageList.add(new LogMessage(LogLevel.WARNING.getLogTemplate()+" "+messageText,LogLevel.WARNING.level) );
        }

        if (message && logMessage) {
            messageList.add(new LogMessage(LogLevel.MESSAGE.getLogTemplate()+" "+messageText,LogLevel.MESSAGE.level) );
        }

        appendList(messageList);

    }

    private void appendList(List<LogMessage> listMessage) {
        System.out.println(listMessage.size());
        listMessage.stream().forEach(x -> {
            if(logToFile) {
                Appender appender = new FileAppender(dbParams);
                appender.append(x.getMessage());
            }

            if(logToConsole) {
                Appender appender = new ConsoleAppender();
                appender.append(x.getMessage());
            }

            if(logToDatabase) {
                Appender appender = new DataBaseAppender();
                appender.append(x.getMessage(), x.getLogLevel()+"");
            }
        });
    }

    @Override
    public String toString() {
        return "JobLogger{" +
                "logToFile=" + logToFile +
                ", logToConsole=" + logToConsole +
                ", logMessage=" + logMessage +
                ", logWarning=" + logWarning +
                ", logError=" + logError +
                ", logToDatabase=" + logToDatabase +
                ", dbParams=" + dbParams +
                '}';
    }
}
