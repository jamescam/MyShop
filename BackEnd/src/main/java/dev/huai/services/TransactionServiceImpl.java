package dev.huai.services;

import dev.huai.daos.TransactionDao;
import dev.huai.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private UserService userService;

    @Override
    public boolean addTransaction(Transaction transaction) {
        return transactionDao.addTransaction(transaction);
    }

    @Override
    public boolean deleteTransactionById(Integer transaction_id) {
        return transactionDao.deleteTransactionById(transaction_id);
    }

    @Override
    public ArrayList<Transaction> getPaidTransactionsByUser(Integer user_id) {
        return transactionDao.getPaidTransactionsByUser(user_id);
    }

    @Override
    public boolean updateTransactionToPaid(Integer transaction_id) {
        return transactionDao.updateTransactionToPaid(transaction_id);
    }

    @Override
    public boolean updateTransactionToPaidByUserId(Integer user_id) {
        ArrayList<Transaction> list = transactionDao.getUnpaidTransactionsByUser(user_id);
        double total = 0;
        for(Transaction ts : list){
            total += ts.getQuantity()*ts.getProduct().getProductPrice().doubleValue();
        }
        System.out.println(total);
        //System.out.println(userService.updateBalanceCashOut(user_id, BigDecimal.valueOf(total)));
        if(userService.updateBalanceCashOut(user_id, BigDecimal.valueOf(total))){
            System.out.println("update balance successful");
            for(Transaction ts : list){
                transactionDao.updateTransactionToPaid(ts.getTransactionId());
            }
            return transactionDao.getUnpaidTransactionsByUser(user_id).isEmpty();
        }else
            return false;



    }

    @Override
    public ArrayList<Transaction> getUnpaidTransactionsByUser(Integer user_id) {
        return transactionDao.getUnpaidTransactionsByUser(user_id);
    }
}
