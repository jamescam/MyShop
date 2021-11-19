package dev.huai.services;

import dev.huai.daos.UserDao;
import dev.huai.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUserById(Integer user_id) {
        return userDao.getUserById(user_id);
    }

    @Override
    public List<User> getUserByManager() {
        return userDao.getUserByManager();
    }

    @Override
    public boolean updateUserPassword(Integer user_id, String password) {
        return userDao.updateUserPassword(user_id, password);
    }

    @Override
    public boolean updateBalanceDeposit(Integer user_id, BigDecimal deposit) {
        return userDao.updateBalanceDeposit(user_id, deposit);
    }

    @Override
    public boolean updateBalanceCashOut(Integer user_id, BigDecimal cash_out_amount) {
        //calculate the cash_out_amount based on quantity of the product, and product
        return userDao.updateBalanceCashOut(user_id, cash_out_amount);
    }

    @Override
    public User getUserByCredential(String email, String password) {
        return userDao.getUserByCredential(email, password);
    }
}
