package upsport.loop.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RatingId implements java.io.Serializable {

    @ManyToOne
    private Upload upload;

    @ManyToOne
    private User user;

    public RatingId(Upload upload, User user) {
        this.upload = upload;
        this.user = user;
    }

    public RatingId() {
    }

    public Upload getUpload() {
        return upload;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}