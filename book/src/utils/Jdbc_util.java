package utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc_util {
    private static DataSource druidDataSource = null;
    static{
        Properties properties = new Properties();
        InputStream in = Jdbc_util.class. getClassLoader().getResourceAsStream("druid.properties");
        System.out.println(in);
        try {
            properties.load(in);
            druidDataSource = DruidDataSourceFactory.createDataSource(properties);
            System.out.println(druidDataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnect() throws IOException, SQLException {
        InputStream in = Jdbc_util.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);
        String JDBC_Url = properties.getProperty("JDBC_Url");
        String JDBC_User = properties.getProperty("JDBC_User");
        String JDBC_Passwd = properties.getProperty("JDBC_Passwd");
        Connection conn = DriverManager.getConnection(JDBC_Url,JDBC_User,JDBC_Passwd);
        return conn;
    }
    public static Connection getConnect1(){
        Connection conn = null;
        try {
            conn = druidDataSource.getConnection();
            System.out.println(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeConn(Connection conn, Statement ps){
        try{
            if (conn != null){
                conn.close();
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            if (ps != null){
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
