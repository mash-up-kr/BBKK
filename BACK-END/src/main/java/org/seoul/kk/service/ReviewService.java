package org.seoul.kk.service;

import org.seoul.kk.dto.review.FeedReviewDto;
import org.seoul.kk.dto.review.ReqReviewDto;

public interface ReviewService {

    FeedReviewDto reviewToPlayLand(ReqReviewDto requestBody);
    FeedReviewDto reviewFeed(Long playLandId, Long cursor, Long size, Boolean rankFlag, Long rankDataSize);
    void deleteReview(Long reviewId, Long travelerId);
}
