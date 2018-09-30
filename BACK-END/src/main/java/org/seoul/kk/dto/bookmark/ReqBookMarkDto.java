package org.seoul.kk.dto.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReqBookMarkDto {

    @NotNull(message = "playland_id is null")
    @JsonProperty(value = "playland_id")
    private Long playLandId;

    @NotNull(message = "traveler_id is null")
    @JsonProperty(value = "traveler_id")
    private Long travelerId;
}
