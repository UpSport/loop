package upsport.loop.business;

import org.hibernate.Session;

import upsport.loop.model.Company;
import upsport.loop.session.TransientSessionExecutor;
import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;

public class CompanyBusiness {

    public void save(final Company company) {
        TransientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.save(company);
            }
        });
    }

    public void update(final Company company) {
        TransientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.update(company);
            }
        });
    }

    public void delete(final Company company) {
        TransientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.delete(company);
            }
        });
    }

    public void deleteById(long id) {
        Company company = new Company(id);
        delete(company);
    }

    public void getById(final long id) {
        TransientSessionExecutor.call(new Getter<Company>() {
            @Override
            public Company get(Session session) {
                return session.get(Company.class, id);
            }
        });
    }
}
