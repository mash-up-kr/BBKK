package org.seoul.kk.dto.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqReviewDto {

    @NotNull(message = "traveler_id is null")
    @JsonProperty(value = "traveler_id")
    private long travelerId;

    @NotNull(message = "playland_id is null")
    @JsonProperty(value = "playland_id")
    private long playLandId;

    @NotNull(message = "content is null")
    private String content;
}
