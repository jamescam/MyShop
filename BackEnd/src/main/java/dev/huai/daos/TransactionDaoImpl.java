package dev.huai.daos;

import dev.huai.models.Product;
import dev.huai.models.Transaction;
import dev.huai.models.User;
import dev.huai.utility.HibernateUtility;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class TransactionDaoImpl implements TransactionDao{
    private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
    private static Session sessionObj;

//    @Override
//    public boolean addTransaction(Transaction transaction) {
//        try{
//            sessionObj = sessionFactory.openSession();
//            sessionObj.beginTransaction();
//            sessionObj.save(transaction);
//            sessionObj.getTransaction().commit();
//            return true;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            sessionObj.getTransaction().rollback();
//        }finally {
//            if(sessionObj!=null)
//                sessionObj.close();
//        }
//        return false;
//    }
    @Override
    public boolean addTransaction(Transaction transaction) {
        try{
            //check if the product is already in the shopping cart
            //update the quantity if it's in the shopping cart
            //add new transaction if not
            ArrayList<Transaction> transactionsCart = getUnpaidTransactionsByUser(transaction.getUser().getUserId());
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            if(transactionsCart.size()>0) {
                System.out.println(transactionsCart.toString());
                for (Transaction ts : transactionsCart) {
                    if (ts.getProduct().getProductId() == transaction.getProduct().getProductId()) {
                        //updating exiting quantity
                        Transaction existTransaction = sessionObj.get(Transaction.class, ts.getTransactionId());
                        if(existTransaction == null)
                            return false;
                        existTransaction.setQuantity(ts.getQuantity()+ transaction.getQuantity());
                        sessionObj.update(existTransaction);
                        sessionObj.getTransaction().commit();
                        return true;
                    }
                }
            }
            //add new transaction
            sessionObj.save(transaction);
            sessionObj.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionObj.getTransaction().rollback();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return false;
    }

    @Override
    public boolean deleteTransactionById(Integer transaction_id) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            Transaction transaction = sessionObj.get(Transaction.class, transaction_id);
            if(transaction == null)
                return false;
            sessionObj.delete(transaction);
            sessionObj.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionObj.getTransaction().rollback();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return false;
    }

    @Override
    public ArrayList<Transaction> getPaidTransactionsByUser(Integer user_id) {
        try{
            ArrayList<Transaction> transactionsList = new ArrayList<>();
            sessionObj = sessionFactory.openSession();
            User user = sessionObj.get(User.class, user_id);
            if(user == null)
                return null;
             //criteria for hibernate 4
//            Criteria criteria = sessionObj.createCriteria(Transaction.class)
//                            .add(Restrictions.eq("user", user))
//                    .add(Restrictions.eq("is_paid", true));

            CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
            CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
            Root<Transaction> root = query.from(Transaction.class);
            query.select(root);
            query.where(builder.equal(root.get("user"), user),builder.equal(root.get("is_paid"), true));
           // query.where(builder.equal(root.get("is_paid"), true));
            Query<Transaction> criteria=sessionObj.createQuery(query);
            for(Iterator iterator = criteria.list().iterator(); iterator.hasNext();){
                Transaction transaction = (Transaction) iterator.next();
                transactionsList.add(transaction);
                System.out.println(transaction.toString());
            }
            return transactionsList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return null;
    }

    @Override
    public ArrayList<Transaction> getUnpaidTransactionsByUser(Integer user_id) {
        try{
            ArrayList<Transaction> transactionsList = new ArrayList<>();
            sessionObj = sessionFactory.openSession();
            User user = sessionObj.get(User.class, user_id);
            if(user == null)
                return null;
            // criteria for hibernate 4
//            Criteria criteria = sessionObj.createCriteria(Transaction.class)
//                            .add(Restrictions.eq("user", user))
//                    .add(Restrictions.eq("is_paid", false));

            CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
            CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
            Root<Transaction> root = query.from(Transaction.class);
            query.select(root);
            query.where(builder.equal(root.get("user"), user), builder.equal(root.get("is_paid"), false));
            Query<Transaction> criteria=sessionObj.createQuery(query);
            for(Iterator iterator = criteria.list().iterator(); iterator.hasNext();){
                Transaction transaction = (Transaction) iterator.next();
                transactionsList.add(transaction);
                System.out.println(transaction.toString());
            }
            return transactionsList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return null;
    }

    @Override
    public boolean updateTransactionToPaid(Integer transaction_id) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            Transaction transaction = sessionObj.get(Transaction.class, transaction_id);
            if(transaction == null)
                return false;
            transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
            transaction.setIs_paid(true);
            sessionObj.update(transaction);
            sessionObj.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            sessionObj.getTransaction().rollback();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return false;
    }


}
