package org.seoul.kk.dto.playland;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.seoul.kk.entity.PlayLand;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedPlayLandDto {

    @JsonProperty(value = "next_cursor")
    private Long nextCursor;

    @JsonProperty(value = "total_size")
    private Long totalSize;

    @JsonProperty(value = "popular_data")
    private List<PlayLand> popularData;

    @JsonProperty(value = "data")
    private List<PlayLand> data;
}
