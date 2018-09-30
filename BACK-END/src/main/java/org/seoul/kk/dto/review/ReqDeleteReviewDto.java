package org.seoul.kk.dto.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReqDeleteReviewDto {

    @NotNull(message = "review_id is null")
    @JsonProperty(value = "review_id")
    private Long reviewId;

    @NotNull(message = "traveler_id is null")
    @JsonProperty(value = "traveler_id")
    private Long travelerId;
}
