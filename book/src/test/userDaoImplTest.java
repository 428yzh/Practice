package test;

import beans.User;
import daoImpl.UserDaoImpl;
import org.junit.Test;

public class userDaoImplTest {
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    @Test
    public void testAdd(){
        User user = new User("yzhim","Aa2766132847");
        int count = userDaoImpl.addOne(user);
        System.out.println(count);
    }
}
