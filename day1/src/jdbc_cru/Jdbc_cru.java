package jdbc_cru;

import jdbc.TestConnect;
import jdbc_util.Jdbc_util;
import org.junit.Test;
import queryForJobs.Jobs;
import queryForJobs.QueryForJobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc_cru {
    @Test
    public void delete() throws IOException {
        InputStream in = TestConnect.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);
        String JDBC_Url = properties.getProperty("JDBC_Url");
        String JDBC_User = properties.getProperty("JDBC_User");
        String JDBC_Passwd = properties.getProperty("JDBC_Passwd");
        try(Connection conn = DriverManager.getConnection(JDBC_Url,JDBC_User,JDBC_Passwd)){
            String delSql = "delete from jobs where job_id=?";
            PreparedStatement a = conn.prepareStatement(delSql);
            a.setObject(1,"SA");    // 问号的参数列
            int n = a.executeUpdate();
            System.out.println("成功了" + n);
        }catch(Exception e){
            System.out.println("失败了");
        }finally {

        }
    }

    @Test
    public void update() throws IOException {
        InputStream in = TestConnect.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);
        String JDBC_Url = properties.getProperty("JDBC_Url");
        String JDBC_User = properties.getProperty("JDBC_User");
        String JDBC_Passwd = properties.getProperty("JDBC_Passwd");
        try(Connection conn = DriverManager.getConnection(JDBC_Url,JDBC_User,JDBC_Passwd)){
            String updateSql = "insert into jobs(job_id) values(?)";
            PreparedStatement a = conn.prepareStatement(updateSql);
            a.setObject(1,"SB");    // 问号的参数列表
            int res = a.executeUpdate();     // 这里改为update
            System.out.println("成功了" + res);
        }catch(Exception e){
            System.out.println("失败了");
        }finally {

        }
    }

    @Test
    public void change() throws IOException {
        InputStream in = TestConnect.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);

        String JDBC_Url = properties.getProperty("JDBC_Url");
        String JDBC_User = properties.getProperty("JDBC_User");
        String JDBC_Passwd = properties.getProperty("JDBC_Passwd");
        try (Connection conn = DriverManager.getConnection(JDBC_Url, JDBC_User, JDBC_Passwd)) {
            String changeSql = "update jobs set job_title=? where job_id=?";
            PreparedStatement a = conn.prepareStatement(changeSql);
            a.setObject(1, "SBT");    // 问号的参数列表
            a.setObject(2, "SB");
            int res = a.executeUpdate();
            System.out.println("成功了" + res);
        } catch (Exception e) {
            System.out.println("false");
        } finally {

        }
    }

    @Test
    public void insertBlob() throws IOException, SQLException {
        Connection conn = Jdbc_util.getConnect();
        String sql = "insert into beauty(name,photo,phone) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,"马思纯");
        FileInputStream imgStream = new FileInputStream(new File("src/1.jpeg"));   // 实际成功了
        ps.setObject(3,"12312312");
        ps.setBlob(2,imgStream);
        System.out.println(ps.executeUpdate());
    }
    @Test
    public void testQueryForJobs(){
        QueryForJobs queryForJobs = QueryForJobs.getInstance();
        String sql = "select * from jobs where job_id=?";
        try {
            Jobs job = queryForJobs.queryJobs(sql, "AC_MGR");
            System.out.println(job);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testQuery(){
        QueryForJobs queryForJobs = QueryForJobs.getInstance();
        String sql = "select count(*) from jobs";
        try{
            Long data = (Long) queryForJobs.query(sql);
            System.out.println(data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

