package upsport.loop.business;

import org.hibernate.Session;

import upsport.loop.model.Rating;
import upsport.loop.model.RatingId;
import upsport.loop.model.Upload;
import upsport.loop.model.User;
import upsport.loop.session.TransientSessionExecutor;
import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;

public class RatingBusiness {

    private TransientSessionExecutor transientSessionExecutor;

    public RatingBusiness(TransientSessionExecutor transientSessionExecutor) {
        this.transientSessionExecutor = transientSessionExecutor;
    }

    public void save(final Rating rating) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.save(rating);
            }
        });
    }

    public void update(final Rating rating) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.update(rating);
            }
        });
    }

    public void delete(final Rating rating) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.delete(rating);
            }
        });
    }

    public void deleteById(long uploadId, long userId) {
        RatingId ratingId = new RatingId(new Upload(uploadId), new User(userId));
        Rating rating = new Rating(ratingId);
        delete(rating);
    }

    // TODO
    public void getById(final long id) {
        transientSessionExecutor.call(new Getter<Rating>() {
            @Override
            public Rating get(Session session) {
                return session.get(Rating.class, id);
            }
        });
    }
}
