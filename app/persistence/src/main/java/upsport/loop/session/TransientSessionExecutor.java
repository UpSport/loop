package upsport.loop.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import upsport.loop.transaction.Task;
import upsport.loop.transaction.TaskExecutor;

public class TransientSessionExecutor {

    private static SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public static void execute(Task task) {
        Session session = sessionFactory.openSession();

        TaskExecutor executor = new TaskExecutor(session);
        executor.execute(task);

        session.disconnect();
        sessionFactory.close();
    }
}
