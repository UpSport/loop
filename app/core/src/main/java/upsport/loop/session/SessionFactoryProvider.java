package upsport.loop.session;

import javax.inject.Singleton;

import org.glassfish.hk2.api.Factory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jvnet.hk2.annotations.Service;

import upsport.loop.model.Company;
import upsport.loop.model.Event;
import upsport.loop.model.Product;
import upsport.loop.model.Rating;
import upsport.loop.model.RatingId;
import upsport.loop.model.Upload;
import upsport.loop.model.User;

@Service
public class SessionFactoryProvider implements Factory<SessionFactory> {

    @Singleton
    @Override
    public SessionFactory provide() {
        // A SessionFactory is set up once for an application!
        // configures settings from hibernate.cfg.xml
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        return new MetadataSources(registry)
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

    @Override
    public void dispose(SessionFactory sessionFactory) {
        sessionFactory.close();
    }
}
