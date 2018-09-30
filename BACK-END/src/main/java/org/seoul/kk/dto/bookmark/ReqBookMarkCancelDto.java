package org.seoul.kk.dto.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReqBookMarkCancelDto {

    @NotNull(message = "bookmark_id is null")
    @JsonProperty(value = "bookmark_id")
    private Long bookmarkId;
}
