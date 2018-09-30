package org.seoul.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "TB_BOOKMARK")
public class BookMark {

    @JsonProperty(value = "bookmark_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "traveler_id" , foreignKey = @ForeignKey(name = "none"))
    private Traveler traveler;

    @ManyToOne
    @JoinColumn(name = "playland_id", foreignKey = @ForeignKey(name = "none"))
    private PlayLand playLand;

    @JsonProperty(value = "mark_at")
    @Column(name = "mark_at")
    private LocalDateTime markAt;

    @PrePersist
    public void onInitEntity() {
        this.markAt = LocalDateTime.now();
    }

}
