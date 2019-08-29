package util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Optional;
import java.util.Properties;

public class ConnectionUtil {

    private static Optional<DataSource> getDataSource() throws Exception {

        try (InputStream input = ConnectionUtil.class.getClassLoader()
                .getResourceAsStream("datasource.properties")) {

            Properties prop = new Properties();
            if (input == null) {
                Optional.empty();
            }
            prop.load(input);
            return Optional.of(BasicDataSourceFactory.createDataSource(prop));

        } catch (IOException ex) {
            throw new IOException("Error with properties file");
        }
    }

    public static Connection getConnection() throws Exception {
        try{
            if(getDataSource().isPresent()){
                return getDataSource().get().getConnection();
            }else{
                throw new Exception("Connection Error");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Connection Error");
        }
    }

}
