package org.seoul.kk.controller;

import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.dto.nickname.ResRandomNicknameDto;
import org.seoul.kk.service.RandomNamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class GenerateNicknameController {

    @Autowired
    @Qualifier(value = "randomNamingServiceImpl")
    private RandomNamingService randomNamingService;

    //TODO 더 이상 만들어질 닉네임이 없을 경우 무한루프가 발생합니다. 이를 수정해야합니다.
    //TODO nickname history 테이블에 데이터를 저장하는 시점은 traveler를 등록할 경우입니다. 이를 수정해야합니다.
    @GetMapping(value = "/generate/nickname")
    public ApiResponseModel<ResRandomNicknameDto> generateNicknameTemp() {

        return ApiResponseModel.<ResRandomNicknameDto>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(randomNamingService.generateRandomNickname())
                .build();
    }
}
