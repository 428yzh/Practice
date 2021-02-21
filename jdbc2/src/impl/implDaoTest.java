package impl;

import Beans.Jobs;
import jdbc_util.Jdbc_util;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class implDaoTest {
    private JobsDaoImpl jobsDao = new JobsDaoImpl();
    @Test
    public void testUpdate() throws SQLException {
        Connection conn = null;
        Jobs aJob = new Jobs("SB","Public Accountant", 4300,9800);
        try {
            conn = Jdbc_util.getConnect1();
            conn.setAutoCommit(false);
            int addOne = jobsDao.addOne(conn, aJob);
            System.out.println("addOne" + addOne);
            aJob.setMin_salary(4600);
            int modifyOne = jobsDao.modifyOne(conn, aJob);
            System.out.println("modifyOne" + modifyOne);
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            conn.setAutoCommit(true);
            Jdbc_util.closeConn(conn, null);
        }
    }
}
