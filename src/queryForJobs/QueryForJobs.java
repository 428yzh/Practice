package queryForJobs;

import jdbc_util.Jdbc_util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryForJobs {
    private static QueryForJobs queryForJobs = new QueryForJobs();
    public static QueryForJobs getInstance(){
        return queryForJobs;
    }

    // 这里应对你需要查询一些比如查询单个字段
    public <E> E query(Connection conn ,String sql, Object...args){
        PreparedStatement ps = null;
        E data = null;
        try{
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++){
                ps.setObject(i+1,args[i]);
            }
            ResultSet res = ps.executeQuery();
            if (res.next()){
                data = (E)res.getObject(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            Jdbc_util.closeConn(null,ps);
        }
        return data;
    }

    // 获取一个对象
    public Jobs queryJobs(Connection conn,String sql, Object...args) throws IOException, SQLException, NoSuchFieldException, IllegalAccessException {
            PreparedStatement ps = null;
            try {
            // 装入参数
                ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ResultSet res = ps.executeQuery();

            // 先获取列数，因为你不知道你查询了多少列，这里用到res的元数据
            if (res.next()) {
                ResultSetMetaData resMetaData = res.getMetaData();    // 获取列数
                int columnCount = resMetaData.getColumnCount();
                Jobs jobs = new Jobs();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = res.getObject(i + 1);   // 获取字段

                    String columnName = resMetaData.getColumnName(i + 1);
                    // 判断这是哪个字段  ,  使用反射
                    Field field = Jobs.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(jobs, columnValue);
                }
                return jobs;
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            Jdbc_util.closeConn(conn,ps);
        }
        return null;
    }

    //     jobs的增删改查操作
    public int updata(Connection conn,String sql, Object...args) throws IOException, SQLException {
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++){
                ps.setObject(i+1,args[i]);
            }
            int n = ps.executeUpdate();
            return n;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            Jdbc_util.closeConn(null,ps);
        }

    }

    // 获取一个对象集合
    public List<Jobs> queryForJobs(Connection conn, String sql, Object...args){
        List <Jobs> jobsList = new ArrayList();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++){
                ps.setObject(i+1,args[i]);
            }
            ResultSet res = ps.executeQuery();
            ResultSetMetaData resdt = res.getMetaData();
            int columnCount = resdt.getColumnCount();
            while(res.next()){
                Jobs job = new Jobs();
                for (int i = 0; i < columnCount; i++){
                    Object columnValue = res.getObject(i+1);   // 第一列为1
                    String columnName = resdt.getColumnLabel(i);    // 使用别名

                    Field field = Jobs.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(job,columnValue);
                }
                jobsList.add(job);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            Jdbc_util.closeConn(null,ps);
        }
        return jobsList;
    }
}

