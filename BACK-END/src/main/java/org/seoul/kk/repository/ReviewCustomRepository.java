package org.seoul.kk.repository;

import org.seoul.kk.dto.review.ResReviewDto;

import java.util.List;

public interface ReviewCustomRepository {

    List<ResReviewDto> findReviewOrderByLikeCntLimitByPlayLandId(long size, long playLandId);
    List<ResReviewDto> findReviewOrderByCreatedAtFromCursorLimitByPlayLandId(long cursor, long size, long playLandId);
    long countByPlayLandId(long playLandId);
}
