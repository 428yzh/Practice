package jdbc;

import jdbc_util.Jdbc_util;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnect {
    public int p = 0;
    @Test
    public static void main(String args[]) throws IOException, SQLException {
        Connection conn = Jdbc_util.getConnect();
        String sql = "select * from jobs";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet res = ps.executeQuery();
        while (res.next()){
            System.out.println(res.getString(1));
        }
        Jdbc_util.closeConn(conn,ps);
    }
}
