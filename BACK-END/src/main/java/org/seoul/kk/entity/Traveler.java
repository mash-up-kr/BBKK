package org.seoul.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.seoul.kk.entity.constant.TravelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_TRAVELER")
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @JsonIgnore
    @Column(name = "uuid")
    private String uuid;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "travel_property")
    private TravelProperty property;

    @JsonIgnore
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //TODO foreign key 제약 해제 하는 방법을 찾아봅니다.
    @JsonIgnore
    @OneToMany(mappedBy = "traveler",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<BookMark> bookMarks = new ArrayList<>();

    public void addBookMark(BookMark bookMark) {
        this.bookMarks.add(bookMark);
        bookMark.setTraveler(this);
    }

    public void removeBookMark(BookMark bookMark) {
        this.bookMarks.remove(bookMark);
        bookMark.setTraveler(this);
    }

    @PrePersist
    public void onInitEntity() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PostUpdate
    public void onUpdateEntity() {
        this.updatedAt = LocalDateTime.now();
    }

}
