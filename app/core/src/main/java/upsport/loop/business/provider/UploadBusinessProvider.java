package upsport.loop.business.provider;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;
import org.hibernate.SessionFactory;
import org.jvnet.hk2.annotations.Service;

import upsport.loop.business.UploadBusiness;
import upsport.loop.session.TransientSessionExecutor;

@Service
public class UploadBusinessProvider implements Factory<UploadBusiness> {

    private final SessionFactory sessionFactory;

    @Inject
    UploadBusinessProvider(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UploadBusiness provide() {
        TransientSessionExecutor transientSessionExecutor = new TransientSessionExecutor(sessionFactory);
        return new UploadBusiness(transientSessionExecutor);
    }

    @Override
    public void dispose(UploadBusiness uploadBusiness) {
        
    }
}
