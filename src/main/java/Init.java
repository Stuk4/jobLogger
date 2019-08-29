import appender.Appender;
import appender.ConsoleAppender;
import util.ConnectionUtil;

import java.sql.Connection;

public class Init {

    static {

    }

    public static void main(String[] args) throws Exception {

        JobLogger jobLogger = new JobLogger();
        jobLogger.logMessage("carlos", false, false, true);

    }

}
