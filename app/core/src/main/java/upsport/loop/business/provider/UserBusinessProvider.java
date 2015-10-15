package upsport.loop.business.provider;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;
import org.hibernate.SessionFactory;
import org.jvnet.hk2.annotations.Service;

import upsport.loop.business.UserBusiness;
import upsport.loop.session.TransientSessionExecutor;

@Service
public class UserBusinessProvider implements Factory<UserBusiness> {

    private final SessionFactory sessionFactory;

    @Inject
    UserBusinessProvider(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserBusiness provide() {
        TransientSessionExecutor transientSessionExecutor = new TransientSessionExecutor(sessionFactory);
        return new UserBusiness(transientSessionExecutor);
    }

    @Override
    public void dispose(UserBusiness userBusiness) {

    }
}
