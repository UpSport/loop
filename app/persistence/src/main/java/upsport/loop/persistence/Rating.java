package upsport.loop.persistence;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "rating_user_upload")
@AssociationOverrides({
        @AssociationOverride(name = "user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "upload",
                joinColumns = @JoinColumn(name = "upload_id")) })
public class Rating {

    private int rating;
    private RatingId RatingId;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public RatingId getRatingId() {
        return RatingId;
    }

    public void setRatingId(RatingId ratingId) {
        RatingId = ratingId;
    }
}
