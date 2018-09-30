package org.seoul.kk.controller;

import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.dto.review.FeedReviewDto;
import org.seoul.kk.dto.review.ReqDeleteReviewDto;
import org.seoul.kk.dto.review.ReqReviewDto;
import org.seoul.kk.exception.BadRequestException;
import org.seoul.kk.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/react")
public class ReactController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping(value = "/review/playland", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponseModel<FeedReviewDto> reviewToPlayLand(@Valid @RequestBody ReqReviewDto requestBody,
                                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        return ApiResponseModel.<FeedReviewDto>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(reviewService.reviewToPlayLand(requestBody))
                .build();
    }

    @GetMapping(value = "/review/feed/{playland_id}")
    public ApiResponseModel<FeedReviewDto> feedReview(@PathVariable(value = "playland_id") Long playLandId,
                                                      @RequestParam(value = "cursor", required = false, defaultValue = "0") Long cursor,
                                                      @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
                                                      @RequestParam(value = "rank-flag", required = false, defaultValue = "true") Boolean rankFlag,
                                                      @RequestParam(value = "rank-data-size", required = false, defaultValue = "3") Long rankDataSize) {

        return ApiResponseModel.<FeedReviewDto>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(reviewService.reviewFeed(playLandId, cursor, size, rankFlag, rankDataSize))
                .build();
    }

    @DeleteMapping(value = "/review/delete")
    public void deleteReview(@Valid @RequestBody ReqDeleteReviewDto requestBody,
                             BindingResult bindingResult,
                             HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        reviewService.deleteReview(requestBody.getReviewId(), requestBody.getTravelerId());
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }
}