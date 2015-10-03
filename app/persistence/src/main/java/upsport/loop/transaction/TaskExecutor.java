package upsport.loop.transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaskExecutor {

    private Session session;

    public TaskExecutor(Session session) {
        this.session = session;
    }

    public void execute(Task task) {
        Transaction transaction = session.beginTransaction();

        task.run(session);

        transaction.commit();
    }
}
