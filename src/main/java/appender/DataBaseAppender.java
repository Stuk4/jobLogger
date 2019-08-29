package appender;

import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;

public class DataBaseAppender implements Appender{

    @Override
    public void append(String... message) {
        try {
            Connection connection = ConnectionUtil.getConnection();
            String query = "insert into Log_Values values (?, ?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, message[0]);
            pstm.setInt(2, Integer.parseInt(message[1]));
            pstm.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving log in database");
        }
    }


}

