package dev.huai.test;
import dev.huai.daos.TransactionDao;
import dev.huai.daos.TransactionDaoImpl;
import dev.huai.models.Product;
import dev.huai.models.Transaction;
import dev.huai.models.User;
import org.junit.Test;

import static junit.framework.TestCase.*;
public class TransactionDaoImplTest {

    private TransactionDao transactionDao = new TransactionDaoImpl();

    @Test
    public void testAddTransaction(){
        User user = new User();
        user.setUserId(1);
        Product product = new Product();
        product.setProductId(1);
        Transaction transaction = new Transaction();
        transaction.setProduct(product);
        transaction.setQuantity(5);
        transaction.setUser(user);
        System.out.println(transaction);
        assertTrue(transactionDao.addTransaction(transaction));
    }
    @Test
    public void testGetPaidTransactionsByUser(){
        assertNotNull(transactionDao.getPaidTransactionsByUser(1));

    }

    @Test
    public void testUpdateTransactionToPaid(){
        assertTrue(transactionDao.updateTransactionToPaid(2));
    }
}
