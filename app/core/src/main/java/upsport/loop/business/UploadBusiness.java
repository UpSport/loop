package upsport.loop.business;

import org.hibernate.Session;

import upsport.loop.model.Upload;
import upsport.loop.session.TransientSessionExecutor;
import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;

public class UploadBusiness {

    public void save(final Upload upload) {
        TransientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.save(upload);
            }
        });
    }

    public void update(final Upload upload) {
        TransientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.update(upload);
            }
        });
    }

    public void delete(final Upload upload) {
        TransientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.delete(upload);
            }
        });
    }

    public void deleteById(long id) {
        Upload upload = new Upload(id);
        delete(upload);
    }

    public void getById(final long id) {
        TransientSessionExecutor.call(new Getter<Upload>() {
            @Override
            public Upload get(Session session) {
                return session.get(Upload.class, id);
            }
        });
    }
}
