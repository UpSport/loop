package upsport.loop.transaction;

import org.hibernate.Session;

public interface Getter<V> {

    V get(Session session);

}
