package org.seoul.kk.entity.relation;

import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.Traveler;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_LIKE_PLAYLAND_RELATION")
public class LikePlayLandRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playland_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private PlayLand playLand;

    @ManyToOne
    @JoinColumn(name = "traveler_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private Traveler traveler;

    @Column(name = "like_at")
    private LocalDateTime likeAt;

    @PrePersist
    public void onInitEntity() {
        this.likeAt = LocalDateTime.now();
    }

}
