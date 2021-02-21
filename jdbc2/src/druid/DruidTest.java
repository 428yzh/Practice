package druid;

import jdbc_util.Jdbc_util;
import org.junit.Test;

import java.sql.Connection;

public class DruidTest {
    @Test
    public void testDruid(){
        Connection conn = Jdbc_util.getConnect1();
        Jdbc_util.closeConn(conn,null);
    }
}
