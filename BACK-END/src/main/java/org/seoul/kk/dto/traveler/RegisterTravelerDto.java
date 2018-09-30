package org.seoul.kk.dto.traveler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTravelerDto {

    @NotNull(message = "nickname is null")
    private String nickname;

    @NotNull(message = "property is null")
    private String property;
}
