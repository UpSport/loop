package upsport.loop.business;

import org.hibernate.Session;

import upsport.loop.model.Event;
import upsport.loop.session.TransientSessionExecutor;
import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;

public class EventBusiness {

    private TransientSessionExecutor transientSessionExecutor;

    public EventBusiness(TransientSessionExecutor transientSessionExecutor) {
        this.transientSessionExecutor = transientSessionExecutor;
    }

    public void save(final Event event) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.save(event);
            }
        });
    }

    public void update(final Event event) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.update(event);
            }
        });
    }

    public void delete(final Event event) {
        transientSessionExecutor.run(new Setter() {
            @Override
            public void set(Session session) {
                session.delete(event);
            }
        });
    }

    public void deleteById(long id) {
        Event event = new Event(id);
        delete(event);
    }

    public void getById(final long id) {
        transientSessionExecutor.call(new Getter<Event>() {
            @Override
            public Event get(Session session) {
                return session.get(Event.class, id);
            }
        });
    }
}
