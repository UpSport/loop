package upsport.loop.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import upsport.loop.persistence.Company;
import upsport.loop.persistence.Event;
import upsport.loop.persistence.Product;
import upsport.loop.persistence.Rating;
import upsport.loop.persistence.RatingId;
import upsport.loop.persistence.Upload;
import upsport.loop.persistence.User;
import upsport.loop.transaction.Task;

public class InsertionTask implements Task {

    @Override
    public void run(Session session) {
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

        session.saveOrUpdate(event);
        session.saveOrUpdate(company);
        session.saveOrUpdate(product);
        session.saveOrUpdate(upload);
        session.save(user);
        session.save(rating);
    }

}
