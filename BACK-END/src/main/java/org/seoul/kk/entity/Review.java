package org.seoul.kk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playland_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private PlayLand playLand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private Traveler traveler;

    @Column(name = "content")
    private String content;

    @Column(name = "like_cnt")
    private Long likeCnt;

    @Column(name = "review_at")
    private LocalDateTime reviewAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onInitEntity() {
        LocalDateTime now = LocalDateTime.now();
        this.reviewAt = now;
        this.updatedAt = now;
        this.likeCnt = 0L;
    }

    @PostUpdate
    public void onUpdateEntity() {
        this.updatedAt = LocalDateTime.now();
    }

}
