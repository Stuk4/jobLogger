import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class JobLoggerTest {

    @Before
    public void beforeEachTestMethod() {
        try {
            Connection connection = ConnectionUtil.getConnection();
            String query ="create table Log_Values (message varchar(100), level long )";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void afterEachTestMethod() {
        try {
            Connection connection = ConnectionUtil.getConnection();
            String query ="drop table Log_Values";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void logMessage() {

        try {
            JobLogger jobLogger = new JobLogger();
            jobLogger.logMessage("carlos",true, true, true);

            Connection connection = ConnectionUtil.getConnection();
            String query ="select count(*) From Log_Values";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int rows= 0;
            if(rs.next()){
                rows = rs.getInt(1);
            }
            assertEquals(3, rows);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}