package org.seoul.kk.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.seoul.kk.dto.review.ResReviewDto;
import org.seoul.kk.entity.QPlayLand;
import org.seoul.kk.entity.QReview;
import org.seoul.kk.entity.QTraveler;
import org.seoul.kk.entity.Review;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReviewCustomRepositoryImpl extends QuerydslRepositorySupport implements ReviewCustomRepository {

    private static final QReview review = QReview.review;
    private static final QTraveler traveler = QTraveler.traveler;
    private static final QPlayLand playland = QPlayLand.playLand;

    public ReviewCustomRepositoryImpl() {
        super(Review.class);
    }

    //TODO 좋아요 수가 같을 경우 정렬 기준을 추가해야합니다.
    @Override
    public List<ResReviewDto> findReviewOrderByLikeCntLimitByPlayLandId(long size, long playLandId) {
        JPQLQuery<ResReviewDto> query = from(review)
                .innerJoin(review.traveler, traveler)
                .where(review.playLand.id.eq(playLandId))
                .orderBy(review.likeCnt.desc())
                .limit(size)
                .select(Projections.constructor(ResReviewDto.class,
                        review.id,
                        review.traveler.id,
                        review.traveler.nickname,
                        review.content,
                        review.likeCnt,
                        review.reviewAt));

        return query.fetch();
    }

    @Override
    public List<ResReviewDto> findReviewOrderByCreatedAtFromCursorLimitByPlayLandId(long cursor, long size, long playLandId) {
        JPQLQuery<ResReviewDto> query = from(review)
                .innerJoin(review.traveler, traveler)
                .where(review.playLand.id.eq(playLandId))
                .orderBy(review.reviewAt.desc())
                .offset(cursor)
                .limit(size)
                .select(Projections.constructor(ResReviewDto.class,
                        review.id,
                        review.traveler.id,
                        review.traveler.nickname,
                        review.content,
                        review.likeCnt,
                        review.reviewAt));

        return query.fetch();
    }

    @Override
    public long countByPlayLandId(long playLandId) {

        return from(review)
                .where(review.playLand.id.eq(playLandId))
                .fetchCount();
    }
}
