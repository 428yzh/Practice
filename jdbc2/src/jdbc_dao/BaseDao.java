package jdbc_dao;

import jdbc_util.Jdbc_util;
import queryForJobs.Jobs;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao <T>{
    private Class<T> clazz = null;
    // 初始化这个clazz
    // 这块代码块，是在子类创建的时候操作的
    {
        Type genericSuperclass  = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type actualTypeArguments[] = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }
    // 获取单个数据
    public <E> E query(Connection conn , String sql, Object...args){
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

    // 查询获取一个对象
    public T queryOne(Connection conn,String sql, Object...args) throws IOException, SQLException, NoSuchFieldException, IllegalAccessException {
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
                T obj = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = res.getObject(i + 1);   // 获取字段

                    String columnName = resMetaData.getColumnName(i + 1);
                    // 判断这是哪个字段  ,  使用反射
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj, columnValue);
                }
                return obj;
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            Jdbc_util.closeConn(conn,ps);
        }
        return null;
    }
    // 获取一个对象集合
    public List<T> queryForT(Connection conn,  String sql, Object...args){
        // 这个sql和参数怎么个传法呢
        List <T> objList = new ArrayList();
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
                T obj = clazz.newInstance();    // 使用newInstance定义一个实例
                for (int i = 0; i < columnCount; i++){
                    Object columnValue = res.getObject(i+1);   // 第一列为1
                    String columnName = resdt.getColumnLabel(i);    // 使用别名
                    Field field = Jobs.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj,columnValue);
                }
                objList.add(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            Jdbc_util.closeConn(null,ps);
        }
        return objList;
    }

    // 通用的增删改操作
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
}
