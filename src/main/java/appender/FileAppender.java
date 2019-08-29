package appender;

import util.FileUtil;

import java.io.IOException;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class FileAppender implements Appender{

    private Map param;


    public FileAppender(Map param) {
        this.param = param;
    }

    @Override
    public void append(String... message){

        FileUtil.initFile(param.get("logFileFolder").toString()+param.get("logFileName").toString());
        FileHandler fh;
        try {
            fh = new FileHandler(param.get("logFileFolder").toString()+param.get("logFileName").toString());
            logger.addHandler(fh);
            logger.log(Level.INFO, message[0]);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating file");
        }

    }

}
