package upsport.loop.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import upsport.loop.transaction.Getter;
import upsport.loop.transaction.Setter;
import upsport.loop.transaction.TaskExecutor;

public class TransientSessionExecutor {

    private static SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public static void run(Setter setter) {
        Session session = sessionFactory.openSession();

        TaskExecutor executor = new TaskExecutor(session);
        executor.run(setter);

        session.disconnect();
        sessionFactory.close();
    }

    public static <T> T call(Getter<T> getter) {
        Session session = sessionFactory.openSession();

        TaskExecutor executor = new TaskExecutor(session);
        T result = executor.call(getter);

        session.disconnect();
        sessionFactory.close();

        return result;
    }
}
