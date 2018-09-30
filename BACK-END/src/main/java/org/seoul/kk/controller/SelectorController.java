package org.seoul.kk.controller;

import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.entity.constant.Season;
import org.seoul.kk.entity.constant.TravelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class SelectorController {

    @GetMapping(value = "/season/list")
    public ApiResponseModel<List<Season>> selectorSeason() {
        List<Season> seasonList = Arrays.asList(Season.values());

        return ApiResponseModel.<List<Season>>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(seasonList)
                .build();
    }

    @GetMapping(value = "/travel-property/list")
    public ApiResponseModel<List<TravelProperty>> selectorTravelProperty() {
        List<TravelProperty> travelPropertyList = Arrays.asList(TravelProperty.values());

        return ApiResponseModel.<List<TravelProperty>>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(travelPropertyList)
                .build();
    }
}
