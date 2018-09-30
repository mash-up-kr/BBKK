package org.seoul.kk.controller;

import lombok.RequiredArgsConstructor;
import org.seoul.kk.common.model.ApiResponseModel;
import org.seoul.kk.dto.bookmark.ReqBookMarkCancelDto;
import org.seoul.kk.dto.bookmark.ReqBookMarkDto;
import org.seoul.kk.entity.BookMark;
import org.seoul.kk.exception.BadRequestException;
import org.seoul.kk.service.BookMarkService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/bookmark")
@RequiredArgsConstructor
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @PostMapping
    public void bookmarkPlayLand(@Valid @RequestBody ReqBookMarkDto requestBody,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        bookMarkService.bookmarkPlayLand(requestBody);
    }

    @DeleteMapping
    public void bookmarkCancel(@Valid @RequestBody ReqBookMarkCancelDto requestBody,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        bookMarkService.bookmarkCancel(requestBody);
    }

    @GetMapping
    public ApiResponseModel<List<BookMark>> retreieveBookMark(@RequestParam(value = "traveler-id", required = false) String travelerId) {
        Long travelerPk;

        if (travelerId == null) {
            throw new BadRequestException();
        }

        try {
            travelerPk = Long.parseLong(travelerId);
        } catch (NumberFormatException e) {
            throw new BadRequestException(e.getMessage());
        }

        return ApiResponseModel.<List<BookMark>>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .result(bookMarkService.retreieveBookmarkList(travelerPk))
                .build();
    }
}
