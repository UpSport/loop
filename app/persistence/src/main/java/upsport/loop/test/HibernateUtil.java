package upsport.loop.test;

import upsport.loop.session.TransientSessionExecutor;

public class HibernateUtil {

    protected static void addUp() {
        InsertionTask task = new InsertionTask();
        TransientSessionExecutor.execute(task);
    }

    protected static void query() {

    }

    public static void main(String args[]) throws Exception {
        addUp();
        query();
    }
}
