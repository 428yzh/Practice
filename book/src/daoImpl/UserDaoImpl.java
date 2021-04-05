package daoImpl;

import beans.User;
import dao.UserDao;
import org.apache.commons.dbutils.QueryRunner;
import utils.Jdbc_util;

import java.sql.Connection;

public class UserDaoImpl implements UserDao {
    @Override
    public int addOne(User user) {
        String sql = "insert into users(username,passwd) values (?,?)";
        Connection conn = null;
        try {
            conn = Jdbc_util.getConnect1();
            QueryRunner queryRunner = new QueryRunner();
            String userName = user.getUserName();
            String passwd = user.getPasswd();
            int count = queryRunner.update(conn,sql,userName,passwd);
            System.out.println(count);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            Jdbc_util.closeConn(conn,null);
        }
    }
}
