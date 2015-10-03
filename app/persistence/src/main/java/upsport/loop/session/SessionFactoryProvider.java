package upsport.loop.session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import upsport.loop.model.Company;
import upsport.loop.model.Event;
import upsport.loop.model.Product;
import upsport.loop.model.Rating;
import upsport.loop.model.RatingId;
import upsport.loop.model.Upload;
import upsport.loop.model.User;

public class SessionFactoryProvider {

    private static SessionFactoryHolder sessionFactoryHolder = new SessionFactoryHolder();

    public static SessionFactory getSessionFactory() {
        return sessionFactoryHolder.getSessionFactory();
    }

    private static class SessionFactoryHolder {

        SessionFactory sessionFactory;

        public SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                // A SessionFactory is set up once for an application!
                // configures settings from hibernate.cfg.xml
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Company.class)
                        .addAnnotatedClass(Event.class)
                        .addAnnotatedClass(Product.class)
                        .addAnnotatedClass(Rating.class)
                        .addAnnotatedClass(RatingId.class)
                        .addAnnotatedClass(Upload.class)
                        .addAnnotatedClass(User.class)
                        .buildMetadata()
                        .buildSessionFactory();
            }
            return sessionFactory;
        }
    }
}
