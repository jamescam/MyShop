package dev.huai.test;

import dev.huai.daos.UserDao;
import dev.huai.daos.UserDaoImpl;
import dev.huai.models.User;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.*;
public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void TestAddUser(){
        User user = new User();
        user.setLastName("A");
        user.setFirstName("B");
        user.setPassword("1234");
        user.setEmail("user@mail.com");
        assertTrue(userDao.addUser(user));
    }

    @Test
    public void TestUpdateUserPassword(){
        assertTrue(userDao.updateUserPassword(1, "1234"));
    }

    @Test
    public void TestUpdateBalanceDeposit(){
        assertTrue(userDao.updateBalanceDeposit(1, new BigDecimal(100)));
    }

    @Test
    public void TestUpdateBalanceCashOut(){
        assertTrue(userDao.updateBalanceCashOut(1,new BigDecimal(20)));
    }

    @Test
    public void TestUpdateBalanceCashOutOver(){
        assertFalse(userDao.updateBalanceCashOut(9,new BigDecimal(10000)));
    }

    @Test
    public void TestGetUserByCredential(){
        assertEquals(new User(2,"customer", "customer", "1234", false, "customer@mail.com"), userDao.getUserByCredential("customer@mail.com", "1234"));
    }

}
