package org.seoul.kk.entity.relation;

import lombok.Data;
import org.seoul.kk.entity.Review;
import org.seoul.kk.entity.Traveler;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_LIKE_REVIEW_RELATION")
public class LikeReviewRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "traveler_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private Traveler traveler;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private Review review;

    @Column(name = "like_at")
    private LocalDateTime likeAt;

    @PrePersist
    public void onInitEntity() {
        this.likeAt = LocalDateTime.now();
    }

}
