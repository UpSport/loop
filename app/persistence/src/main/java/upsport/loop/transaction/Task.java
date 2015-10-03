package upsport.loop.transaction;

import org.hibernate.Session;

public interface Task {
    void run(Session session);
}
