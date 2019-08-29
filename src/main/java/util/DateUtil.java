package util;

import java.text.DateFormat;
import java.util.Date;

public class DateUtil {

    public static String nowAsString(){
        return DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
    }

}
