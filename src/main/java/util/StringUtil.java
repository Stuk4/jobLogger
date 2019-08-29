package util;

public class StringUtil {

    public static boolean isEmpty(String message){
        if (message == null || message.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean booleanFromString(String value){
        if(value==null){
            return false;
        }
        return Boolean.parseBoolean(value);
    }

}
