package upsport.loop.test;

import org.hibernate.SessionFactory;

import upsport.loop.business.StatisticBusiness;
import upsport.loop.model.Event;
import upsport.loop.model.Upload;
import upsport.loop.session.SessionFactoryProvider;
import upsport.loop.session.TransientSessionExecutor;

public class MainTester {

    private static SessionFactory sessionFactory;
    private static TransientSessionExecutor transientSessionExecutor;

    protected static void addUp() {
        InsertionTask task = new InsertionTask();
        transientSessionExecutor.run(task);
    }

    protected static void query() {
        StatisticBusiness stat = new StatisticBusiness(transientSessionExecutor);
        for (Upload upload : stat.getTopNUpload(10)) {
            System.out.println(upload.getId() + "-" + upload.getName() + "-" + upload.getAverageRating());
        }

        for (Upload upload : stat.getTopNUploadByEventId(10, 40)) {
            System.out.println(upload.getId() + "-" + upload.getName() + "-" + upload.getAverageRating());
        }

        Event event = new Event(40);
        for (Upload upload : stat.getTopNUploadByEvent(10, event)) {
            System.out.println(upload.getId() + "-" + upload.getName() + "-" + upload.getAverageRating());
        }
    }

    public static void main(String args[]) throws Exception {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        transientSessionExecutor = new TransientSessionExecutor(sessionFactory);

        // addUp();
        query();

        sessionFactory.close();
    }
}
