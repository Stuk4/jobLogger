package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {

    static Logger LOG = Logger.getLogger("MyLog");

    public static void initFile(String filePath){

        Path path = Paths.get(filePath);
        if(!Files.exists(path)){
            try {
                File f = new File(filePath);
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                LOG.log(Level.SEVERE, "Error valindado archivo");
            }
        }

    }



}
