package upsport.loop.business;

import org.hibernate.Session;

import upsport.loop.model.Company;
import upsport.loop.session.TransientSessionExecutor;
import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;

public class CompanyBusiness {

    private TransientSessionExecutor transientSessionExecutor;

    public CompanyBusiness(TransientSessionExecutor transientSessionExecutor) {
        this.transientSessionExecutor = transientSessionExecutor;
    }

    public void save(final Company company) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.save(company);
            }
        });
    }

    public void update(final Company company) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.update(company);
            }
        });
    }

    public void delete(final Company company) {
        transientSessionExecutor.run(new Setter() {
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
        transientSessionExecutor.call(new Getter<Company>() {
            @Override
            public Company get(Session session) {
                return session.get(Company.class, id);
            }
        });
    }
}
