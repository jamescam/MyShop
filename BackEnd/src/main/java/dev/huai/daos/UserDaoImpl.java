package dev.huai.daos;

import dev.huai.models.Transaction;
import dev.huai.models.User;
import dev.huai.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
    private static Session sessionObj;

    @Override
    public boolean addUser(User user) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            sessionObj.save(user);
            sessionObj.getTransaction().commit();
            return true;
        }catch(Exception sqlException){
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return false;
    }

    @Override
    public User getUserById(Integer user_id) {
        try{
            sessionObj = sessionFactory.openSession();
            User user = sessionObj.get(User.class, user_id);
            return user;
        }catch(Exception sqlException){
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return null;
    }

    @Override
    public boolean updateUserPassword(Integer user_id, String newPassword) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            User user = sessionObj.get(User.class, user_id);
            user.setPassword(newPassword);
            sessionObj.update(user);
            sessionObj.getTransaction().commit();
            return true;
        }catch(Exception sqlException){
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return false;
    }

    @Override
    public boolean updateBalanceDeposit(Integer user_id, BigDecimal deposit) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            User user = sessionObj.get(User.class, user_id);
            user.setBalance(user.getBalance().add(deposit));
            sessionObj.update(user);
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
    public boolean updateBalanceCashOut(Integer user_id, BigDecimal cash_out_amount) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            User user = sessionObj.get(User.class, user_id);
            // Check if there is enough fund for purchasing
            System.out.println(user.toString());
            System.out.println("cash out amount"+cash_out_amount);
            System.out.println(user.getBalance().subtract(cash_out_amount));
            if(user.getBalance().subtract(cash_out_amount).compareTo(new BigDecimal(0)) < 0){
                System.out.println("No enough balance");
                return false;
            }

            user.setBalance(user.getBalance().subtract(cash_out_amount));
            sessionObj.update(user);
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
    public User getUserByCredential(String email, String password) {
        try{
            sessionObj = sessionFactory.openSession();
            CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root);
            query.where(builder.equal(root.get("email"), email));
            Query<User> criteria =sessionObj.createQuery(query);
            User user = criteria.list().get(0);

            //User user = sessionObj.get(User.class, email);
            //System.out.println(user.toString());
            if(user.getPassword().equals(password))
                return user;
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return null;
    }

    @Override
    public List<User> getUserByManager(){
        try{
            sessionObj = sessionFactory.openSession();
            CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root);
            query.where(builder.equal(root.get("isManager"), false));
            Query<User> criteria=sessionObj.createQuery(query);
            return criteria.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            if(sessionObj!=null)
                sessionObj.close();
        }
        return null;
    }

}
