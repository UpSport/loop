package upsport.loop.transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaskExecutor {

    private Session session;

    public TaskExecutor(Session session) {
        this.session = session;
    }

    public void run(Setter setter) {
        Transaction transaction = session.beginTransaction();

        setter.set(session);

        transaction.commit();
    }

    public <T> T call(Getter<T> getter) {
        return getter.get(session);
    }

}
