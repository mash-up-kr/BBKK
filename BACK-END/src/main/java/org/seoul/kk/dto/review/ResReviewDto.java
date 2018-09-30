package org.seoul.kk.dto.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ResReviewDto {

    private long id;

    @JsonProperty(value = "traveler_id")
    private long travelerId;

    private String nickname;

    private String content;

    @JsonProperty(value = "like_cnt")
    private long likeCnt;

    @JsonProperty(value = "like_status")
    private boolean likeStatus;

    @JsonProperty(value = "review_at")
    private LocalDateTime reviewAt;

    public ResReviewDto(long id, long travelerId, String nickname, String content, long likeCnt, LocalDateTime reviewAt) {
        this.id = id;
        this.travelerId = travelerId;
        this.nickname = nickname;
        this.content = content;
        this.likeCnt = likeCnt;
        this.likeStatus = false;
        this.reviewAt = reviewAt;
    }

    public ResReviewDto() {
    }
}
