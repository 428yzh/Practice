package dbutils;

import jdbc_util.Jdbc_util;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;

public class dbutilsTest {
    @Test
    public void testDbutils(){
        QueryRunner queryRunner = new QueryRunner();
        Connection conn = Jdbc_util.getConnect1();
        String sql1 = "update jobs set job_id='SBB' where job_id='SB'";
        String sql2 = "select * from jobs";
        try {
            int updateCount = queryRunner.update(conn,sql1);
            System.out.println(updateCount);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
