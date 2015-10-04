package upsport.loop.business;

import java.util.List;

import upsport.loop.model.Event;
import upsport.loop.model.Upload;
import upsport.loop.session.TransientSessionExecutor;

public class StatisticBusiness {

    private TransientSessionExecutor transientSessionExecutor;

    public StatisticBusiness(TransientSessionExecutor transientSessionExecutor) {
        this.transientSessionExecutor = transientSessionExecutor;
    }

    // TODO why need all field ...
    public List<Upload> getTopNUpload(int n) {
        return transientSessionExecutor
                .execute(
                        "SELECT AVG(rating_user_upload.rating) AS averageRating, "
                                + "upload.upload_id, upload.upload_name, upload.upload_time, upload.upload_type, upload.sport_category, upload.upload_path, upload.event_id, rating_user_upload.user_id, "
                                + "user.user_id, user.user_name, user.user_email, user.user_password "
                                + "FROM upload, rating_user_upload, user "
                                + "WHERE upload.upload_id = rating_user_upload.upload_id and user.user_id = rating_user_upload.user_id "
                                + "ORDER BY averageRating "
                                + "LIMIT " + n,
                        new Class[] { Upload.class });
    }

    public List<Upload> getTopNUploadByEvent(int n, Event event) {
        return getTopNUploadByEventId(n, event.getId());
    }

    public List<Upload> getTopNUploadByEventId(int n, long eventId) {
        return transientSessionExecutor
                .execute(
                        "SELECT AVG(rating_user_upload.rating) AS averageRating, "
                                + "upload.upload_id, upload.upload_name, upload.upload_time, upload.upload_type, upload.sport_category, upload.upload_path, upload.event_id, "
                                + "user.user_id, user.user_name, user.user_email, user.user_password "
                                + "FROM upload, rating_user_upload, user, event "
                                + "WHERE upload.upload_id = rating_user_upload.upload_id and user.user_id = rating_user_upload.user_id and upload.event_id = "
                                + eventId + " "
                                + "ORDER BY averageRating LIMIT " + n,
                        new Class[] { Upload.class });
    }
}
