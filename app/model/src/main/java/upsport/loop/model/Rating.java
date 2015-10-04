package upsport.loop.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "rating_user_upload")
@AssociationOverrides({
        @AssociationOverride(name = "id.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "id.upload",
                joinColumns = @JoinColumn(name = "upload_id")) })
public class Rating {

    @Column(name = "rating")
    private int rating;

    @EmbeddedId
    private RatingId id;

    public Rating() {
    }

    public Rating(RatingId id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public RatingId getId() {
        return id;
    }

    public void setId(RatingId id) {
        this.id = id;
    }

}
