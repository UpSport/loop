package upsport.loop.business.provider;

import org.glassfish.hk2.api.Factory;
import org.hibernate.SessionFactory;
import org.jvnet.hk2.annotations.Service;

import upsport.loop.business.UserBusiness;
import upsport.loop.session.SessionFactoryProvider;
import upsport.loop.session.TransientSessionExecutor;

@Service
public class UserBusinessProvider implements Factory<UserBusiness> {

    @Override
    public void dispose(UserBusiness arg0) {

    }

    @Override
    public UserBusiness provide() {
        SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
        TransientSessionExecutor transientSessionExecutor = new TransientSessionExecutor(sessionFactory);
        return new UserBusiness(transientSessionExecutor);
    }

}
