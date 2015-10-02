package upsport.loop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import upsport.loop.persistence.Event;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        // configures settings from hibernate.cfg.xml
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Event.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had
            // trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void main(String args[]) throws Exception {
        setUp();
        Session session = sessionFactory.openSession();

        Event event = new Event();
        event.setName("Test");
        event.setDescription("Test");

        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(event);

        transaction.commit();

        session.flush();
        session.close();
        
        sessionFactory.close();
    }
}
