package org.seoul.kk.controller;

import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.dto.traveler.RegisterTravelerDto;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.entity.constant.TravelProperty;
import org.seoul.kk.exception.BadRequestException;
import org.seoul.kk.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/traveler")
public class TravelerController {

    @Autowired
    private TravelerService travelerService;

    @GetMapping(value = "/confirm")
    public ApiResponseModel<Traveler> confirmTraveler(@RequestHeader("uuid") String uuid) {

        return ApiResponseModel.<Traveler>builder()
                .code(HttpStatus.CONFLICT.value())
                .msg(HttpStatus.CONFLICT.getReasonPhrase())
                .result(travelerService.getTravelerByUuid(uuid))
                .build();
    }

    @PostMapping(value = "/register")
    public ApiResponseModel<Traveler> registerTraveler(@RequestHeader("uuid") String uuid,
                                                       @Valid @RequestBody RegisterTravelerDto requestBody,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        try {
            TravelProperty.valueOf(requestBody.getProperty());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("여행자 타입 enum값이 형식이 맞지 않습니다.");
        }

        return ApiResponseModel.<Traveler>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(travelerService.registerTraveler(requestBody, uuid))
                .build();
    }
}