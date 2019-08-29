public class LogMessage {

    private String message;
    private int logLevel;

    public LogMessage(String message, int logLevel) {
        this.message = message;
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public int getLogLevel() {
        return logLevel;
    }
}
