package util;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void testinitFile(){
        String path = "E:\\tmp\\text.txt";
        FileUtil.initFile(path);
        boolean success = Files.exists(Paths.get(path));
        assertTrue(success);
        try {
            if(success){
                Files.delete(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}