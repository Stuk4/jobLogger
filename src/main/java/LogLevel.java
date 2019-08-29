import util.DateUtil;

public enum LogLevel {


    ERROR(2, "error"),
    WARNING(3,"warning"),
    MESSAGE(1,"message");

    public final int level;
    public final String message;


    LogLevel(int level, String message) {
        this.level = level;
        this.message = message;
    }

    public String getLogTemplate(){
        return message+" "+ DateUtil.nowAsString();
    }

}
