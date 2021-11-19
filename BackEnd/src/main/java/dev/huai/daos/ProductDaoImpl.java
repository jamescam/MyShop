package dev.huai.daos;

import dev.huai.models.Product;
import dev.huai.models.Transaction;
import dev.huai.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class ProductDaoImpl implements ProductDao{


    private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
    private static Session sessionObj;

    @Override
    public boolean addProduct(Product product) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            sessionObj.save(product);
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
    public boolean updateProductPrice(Integer product_id, BigDecimal newPrice) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            Product product = sessionObj.get(Product.class, product_id);
            product.setProductPrice(newPrice);
            sessionObj.update(product);
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
    public boolean deleteProductById(Integer product_id) {
        try{
            sessionObj = sessionFactory.openSession();
            sessionObj.beginTransaction();
            Product product = sessionObj.get(Product.class, product_id);
            if(product == null)
                return false;
            sessionObj.delete(product);
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
    public ArrayList<Product> getAllProduct() {
        try {
            ArrayList<Product> productList = new ArrayList<>();
            sessionObj = sessionFactory.openSession();
            Query<Product> query = sessionObj.createQuery("from Product");
            for (Iterator iterator = query.list().iterator(); iterator.hasNext(); ) {
                Product product = (Product) iterator.next();
                productList.add(product);
            }
            return productList;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (sessionObj != null)
                sessionObj.close();
        }
        return null;
    }
}
