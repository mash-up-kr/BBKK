package org.seoul.kk.dto.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedReviewDto {

    @JsonProperty(value = "next_cursor")
    private Long nextCursor;

    @JsonProperty(value = "total_size")
    private Long totalSize;

    @JsonProperty(value = "popular_review")
    private List<ResReviewDto> popularReview;

    @JsonProperty(value = "review")
    private List<ResReviewDto> review;
}
