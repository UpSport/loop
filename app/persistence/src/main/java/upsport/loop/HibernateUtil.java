package upsport.loop;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import upsport.loop.persistence.Company;
import upsport.loop.persistence.Event;
import upsport.loop.persistence.Product;
import upsport.loop.persistence.Rating;
import upsport.loop.persistence.RatingId;
import upsport.loop.persistence.Upload;
import upsport.loop.persistence.User;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        // configures settings from hibernate.cfg.xml
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
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
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had
            // trouble building the SessionFactory
            // so destroy it manually.
            System.out.println(e.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void main(String args[]) throws Exception {
        setUp();
        Session session = sessionFactory.openSession();

        // event
        Event event = new Event();
        event.setName("Test");
        event.setDescription("Test");

        // company
        Company company = new Company();
        company.setName("Test");
        company.setDescription("Test");

        // product
        Product product = new Product();
        product.setName("Test");
        product.setCompany(company);
        product.setDescription("Test");

        // upload
        Upload upload = new Upload();
        upload.setName("Test");
        upload.setType("photo");
        upload.setEvent(event);
        Set<Product> products = new HashSet<>();
        products.add(product);
        upload.setProducts(products);
        upload.setTime(new Date(System.currentTimeMillis()));

        // user
        User user = new User();
        user.setName("Test");
        user.setPassword("Test");
        user.setEmail("Test");

        // rating
        RatingId ratingId = new RatingId();
        ratingId.setUpload(upload);
        ratingId.setUser(user);

        Rating rating = new Rating();
        rating.setRating(5);
        rating.setRatingId(ratingId);

        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(event);
        session.saveOrUpdate(company);
        session.saveOrUpdate(product);
        session.saveOrUpdate(upload);
        session.save(user);
        session.save(rating);

        transaction.commit();

        session.flush();
        session.close();

        sessionFactory.close();
    }
}
