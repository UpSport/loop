package upsport.loop.business;

import org.hibernate.Session;

import upsport.loop.model.User;
import upsport.loop.session.TransientSessionExecutor;
import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;

public class UserBusiness {

    private TransientSessionExecutor transientSessionExecutor;

    public UserBusiness(TransientSessionExecutor transientSessionExecutor) {
        this.transientSessionExecutor = transientSessionExecutor;
    }

    public void save(final User user) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.save(user);
            }
        });
    }

    public void update(final User user) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.update(user);
            }
        });
    }

    public void delete(final User user) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.delete(user);
            }
        });
    }

    public void deleteById(long id) {
        User user = new User(id);
        delete(user);
    }

    public void getById(final long id) {
        transientSessionExecutor.call(new Getter<User>() {
            @Override
            public User get(Session session) {
                return session.get(User.class, id);
            }
        });
    }
}