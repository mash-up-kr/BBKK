package org.seoul.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.seoul.kk.entity.constant.Season;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_PLAYLAND")
public class PlayLand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "category")
    private String category;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "season", nullable = false)
    private Season season;

    //TODO LAZY loading으로 수정해야합니다.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "traveler_id", nullable = false, foreignKey = @ForeignKey(name = "none"))
    private Traveler traveler;

    @NotNull
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "position")
    private String position;

    @JsonProperty(value = "review_cnt")
    @Column(name = "review_cnt")
    private Long reviewCnt;

    @JsonProperty(value = "image_url")
    @Column(name = "image_url")
    private String imageUrl;

    @JsonProperty(value = "created_at")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    public void onInitEntity() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.reviewCnt = 0L;
    }

    @PostUpdate
    public void onUpdateEntity() {
        this.updatedAt = LocalDateTime.now();
    }

}
