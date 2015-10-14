package upsport.loop.business.provider;

import org.glassfish.hk2.api.Factory;
import org.hibernate.SessionFactory;
import org.jvnet.hk2.annotations.Service;

import upsport.loop.business.UploadBusiness;
import upsport.loop.session.SessionFactoryProvider;
import upsport.loop.session.TransientSessionExecutor;

@Service
public class UploadBusinessProvider implements Factory<UploadBusiness> {

    @Override
    public void dispose(UploadBusiness arg0) {

    }

    @Override
    public UploadBusiness provide() {
        SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
        TransientSessionExecutor transientSessionExecutor = new TransientSessionExecutor(sessionFactory);
        return new UploadBusiness(transientSessionExecutor);
    }

}
