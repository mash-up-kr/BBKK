package org.seoul.kk.dto.playland;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPlayLandDto {

    @NotNull(message = "travelerId is null")
    @JsonProperty(value = "traveler_id")
    private Long travelerId;

    @NotNull(message = "title is null")
    private String title;

    @NotNull(message = "category is null")
    private String category;

    @NotNull(message = "season is null")
    private String season;

    @NotNull(message = "content is null")
    private String content;

    @NotNull(message = "position is null")
    private String position;

    @NotNull(message = "images are null")
    private String images;

    public static RegisterPlayLandDto newInstance(Long travelerId, String title, String season, String content, String position, String images, String category) {
        return new RegisterPlayLandDto(travelerId, title, season, content, position, images, category);
    }

}
