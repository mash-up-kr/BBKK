package org.seoul.kk.controller;

import org.apache.commons.codec.binary.Base64;
import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.dto.playland.FeedPlayLandDto;
import org.seoul.kk.dto.playland.RegisterPlayLandDto;
import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.entity.constant.Season;
import org.seoul.kk.exception.BadRequestException;
import org.seoul.kk.service.PlayLandService;
import org.seoul.kk.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/v1/playland")
public class PlayLandController {

    private final PlayLandService playLandService;
    private final TravelerService travelerService;

    @Autowired
    public PlayLandController(PlayLandService playLandService,
                              TravelerService travelerService) {
        this.playLandService = playLandService;
        this.travelerService = travelerService;
    }

    //TODO split 관련 메서드를 좀 더 효율적으로 처리해야합니다.
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void registerPlayLand(@Valid @RequestBody RegisterPlayLandDto requestBody,
                                 BindingResult bindingResult,
                                 HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("PlayLand를 등록시 필수 파라미터를 채워주세요.");
        }

        if (!isBase64Encoded(requestBody.getImages())) {
            throw new BadRequestException("이미지가 base64로 인코딩 되지않았습니다.");
        }

        if (requestBody.getImages().split(",").length > 5) {
            throw new BadRequestException("이미지를 5장이상 업로드 할 수 없습니다.");
        }

        try {
            Season.valueOf(requestBody.getSeason());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("계절 enum 값의 형식이 맞지 않습니다.");
        }


        Traveler traveler = travelerService.getTravelerById(requestBody.getTravelerId());
        playLandService.registerPlayLand(requestBody, traveler);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponseModel<PlayLand> updatePlayLand(@Valid @RequestBody PlayLand requestBody,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("PlayLand를 수정시 필수 파라미터를 채워주세요.");
        }

        //TODO enum validation이 필요합니다.
        //TODO 작성자와 수정자가 같은지 확인하는 validation이 필요합니다.
        return ApiResponseModel.<PlayLand>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(playLandService.updatePlayLand(requestBody))
                .build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deletePlayLand(@PathVariable(name = "id") long playLandId,
                               HttpServletResponse response) {
        playLandService.deletePlayLand(playLandId);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    //TODO 필터기능을 추가해야합니다.
    @GetMapping(value = "/feed", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponseModel<FeedPlayLandDto> feedPlanLand(@RequestParam(value = "cursor", required = false, defaultValue = "0") Long cursor,
                                                          @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
                                                          @RequestParam(value = "rank_flag", required = false, defaultValue = "true") Boolean rankFlag,
                                                          @RequestParam(value = "rank_data_size", required = false, defaultValue = "3") Long rankDataSize,
                                                          @RequestParam(value = "season", required = false) String season) {
        ApiResponseModel<FeedPlayLandDto> response = ApiResponseModel.<FeedPlayLandDto>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .build();

        if (season != null) {
            Season filterSeason;

            try {
                filterSeason = Season.valueOf(season);
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("계절 enum 값의 형식이 맞지 않습니다.");
            }

            response.setResult(playLandService.feedPlayLandBySeason(cursor, size, rankFlag, rankDataSize, filterSeason));
            return response;
        } else {
            response.setResult(playLandService.feedPlayLand(cursor, size, rankFlag, rankDataSize));
            return response;
        }
    }

    private boolean isBase64Encoded(String images) {
        return Arrays.stream(images.split(",")).allMatch(Base64::isBase64);
    }
}
