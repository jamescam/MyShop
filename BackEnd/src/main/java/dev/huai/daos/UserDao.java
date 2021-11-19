package dev.huai.daos;

import dev.huai.models.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao {
    public boolean addUser(User user);

    public User getUserById(Integer user_id);

    public List<User> getUserByManager();

    public boolean updateUserPassword(Integer user_id, String password);

    public boolean updateBalanceDeposit(Integer user_id, BigDecimal deposit);

    public boolean updateBalanceCashOut(Integer user_id, BigDecimal cash_out_amount);

    public User getUserByCredential(String email, String password);

}
