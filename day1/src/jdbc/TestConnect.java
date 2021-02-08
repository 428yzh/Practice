package jdbc;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TestConnect {
    @Test
    public static void main(String args[]) throws IOException {
        InputStream in = TestConnect.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);

        String JDBC_Url = properties.getProperty("JDBC_Url");
        String JDBC_User = properties.getProperty("JDBC_User");
        String JDBC_Passwd = properties.getProperty("JDBC_Passwd");
        try(Connection conn = DriverManager.getConnection(JDBC_Url,JDBC_User,JDBC_Passwd)){
            PreparedStatement a = conn.prepareStatement("select * from jobs");
            ResultSet res = a.executeQuery("select * from jobs");
            while(res.next()){
                System.out.println(res.getString(1));
            }
        }catch(Exception e){

        }finally {

        }
    }
}
