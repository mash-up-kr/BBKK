package org.seoul.kk.service;

import lombok.RequiredArgsConstructor;
import org.seoul.kk.dto.review.FeedReviewDto;
import org.seoul.kk.dto.review.ReqReviewDto;
import org.seoul.kk.dto.review.ResReviewDto;
import org.seoul.kk.entity.Review;
import org.seoul.kk.exception.ForbiddenException;
import org.seoul.kk.exception.NotAcceptableException;
import org.seoul.kk.exception.NotFoundPlayLand;
import org.seoul.kk.exception.NotFoundTraveler;
import org.seoul.kk.repository.PlayLandRepository;
import org.seoul.kk.repository.ReviewRepository;
import org.seoul.kk.repository.TravelerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlayLandRepository playLandRepository;
    private final TravelerRepository travelerRepository;

    @Override
    public FeedReviewDto reviewToPlayLand(ReqReviewDto requestBody) {
        long playLandId = requestBody.getPlayLandId();
        long nextCursor = 0L;

        Review review = Review.builder()
                .playLand(playLandRepository.findById(playLandId)
                        .orElseThrow(NotFoundPlayLand::new))
                .traveler(travelerRepository.findById(requestBody.getTravelerId())
                        .orElseThrow(NotFoundTraveler::new))
                .content(requestBody.getContent())
                .build();

        reviewRepository.save(review);
        List<ResReviewDto> reviews = reviewRepository.findReviewOrderByCreatedAtFromCursorLimitByPlayLandId(0L, 10, playLandId);
        long resultSize = (long) reviews.size();
        long totalSize = reviewRepository.countByPlayLandId(playLandId);

        if (resultSize == 10 && resultSize != totalSize) {
            nextCursor += resultSize;
        }

        return FeedReviewDto.builder()
                .nextCursor(nextCursor)
                .totalSize(totalSize)
                .popularReview(reviewRepository.findReviewOrderByLikeCntLimitByPlayLandId(3L, playLandId))
                .review(reviews)
                .build();
    }

    @Override
    public FeedReviewDto reviewFeed(Long playLandId, Long cursor, Long size, Boolean rankFlag, Long rankDataSize) {
        List<ResReviewDto> reviews = reviewRepository.findReviewOrderByCreatedAtFromCursorLimitByPlayLandId(cursor, size, playLandId);
        long nextCursor = cursor;
        long totalSize = reviewRepository.countByPlayLandId(playLandId);

        if (nextCursor >= totalSize ) {
            throw new NotAcceptableException();
        }

        if (reviews.size() == size && reviews.size() != totalSize) {
            nextCursor += size;
        }

        FeedReviewDto response = FeedReviewDto.builder()
                .nextCursor(nextCursor)
                .totalSize(totalSize)
                .review(reviews)
                .build();

        if (rankFlag) {
            response.setPopularReview(reviewRepository.findReviewOrderByLikeCntLimitByPlayLandId(rankDataSize, playLandId));
        }

        return response;
    }

    @Override
    public void deleteReview(Long reviewId, Long travelerId) {
        Review review = reviewRepository.findByIdAndTravelerId(reviewId, travelerId).orElseThrow(ForbiddenException::new);
        reviewRepository.deleteById(review.getId());
    }
}
