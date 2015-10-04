package upsport.loop.business;

import org.hibernate.Session;

import upsport.loop.model.Product;
import upsport.loop.session.TransientSessionExecutor;
import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;

public class ProductBusiness {

    private TransientSessionExecutor transientSessionExecutor;

    public ProductBusiness(TransientSessionExecutor transientSessionExecutor) {
        this.transientSessionExecutor = transientSessionExecutor;
    }

    public void save(final Product product) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.save(product);
            }
        });
    }

    public void update(final Product product) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.update(product);
            }
        });
    }

    public void delete(final Product product) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.delete(product);
            }
        });
    }

    public void deleteById(final long id) {
        Product product = new Product(id);
        delete(product);
    }

    public void getById(final long id) {
        transientSessionExecutor.call(new Getter<Product>() {
            @Override
            public Product get(Session session) {
                return session.get(Product.class, id);
            }
        });
    }
}
