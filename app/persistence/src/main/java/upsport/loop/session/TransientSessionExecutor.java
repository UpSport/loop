package upsport.loop.session;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;
import upsport.loop.transaction.TaskExecutor;

// TODO injection: maven-jar-plugin generated jar dependency sequence had a bug
// core->persistence, while persistence is after core, so moved all DI into core at this moment,
// should research on how to make everything into DI while able to debug in IDE, and able to run app in CLI.
public class TransientSessionExecutor {

    private SessionFactory sessionFactory;

    public TransientSessionExecutor(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void run(Setter setter) {
        Session session = sessionFactory.openSession();

        TaskExecutor executor = new TaskExecutor(session);
        executor.run(setter);

        session.disconnect();
    }

    public <T> T call(Getter<T> getter) {
        Session session = sessionFactory.openSession();

        TaskExecutor executor = new TaskExecutor(session);
        T result = executor.call(getter);

        session.disconnect();

        return result;
    }

    public <T> List<T> execute(String queryString, Class[] entityTypes, String... scalars) {
        Session session = sessionFactory.openSession();

        SQLQuery query = session.createSQLQuery(queryString);

        for (Class entityType : entityTypes) {
            query.addEntity(entityType);
        }

        for (String scalar : scalars) {
            query.addScalar(scalar);
        }

        // .setParameter("stockCode", "7277");
        List<T> result = query.list();

        session.disconnect();

        return result;
    }
}
