package dev.huai.utility;

import dev.huai.models.Product;
import dev.huai.models.Transaction;
import dev.huai.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

public class HibernateUtility {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {

        if (sessionFactory == null)
        {

            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            String url = System.getenv("Project2DB_URL");
            String username = System.getenv("Project2DB_Username");
            String password = System.getenv("Project2DB_Password");
            //String ddl = System.getenv("Project2DDL");

            settings.put(Environment.URL, url);
            settings.put(Environment.USER, username);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

            settings.put(Environment.SHOW_SQL, "true");
            //settings.put(Environment.HBM2DDL_AUTO, ddl);
            // set to create when first time running
            // change to validate after

            sessionFactory =  new Configuration()
                    .setProperties(settings)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Transaction.class)
                    .buildSessionFactory();
        }
        return  sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
