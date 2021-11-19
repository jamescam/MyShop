package dev.huai.daos;

import dev.huai.models.Transaction;
import dev.huai.models.User;

import java.util.ArrayList;

public interface TransactionDao {

    public boolean addTransaction(Transaction transaction);

    public boolean deleteTransactionById(Integer transaction_id);

    public ArrayList<Transaction> getPaidTransactionsByUser(Integer user_id);

    public boolean updateTransactionToPaid(Integer transaction_id);

    public ArrayList<Transaction> getUnpaidTransactionsByUser(Integer user_id);


}
