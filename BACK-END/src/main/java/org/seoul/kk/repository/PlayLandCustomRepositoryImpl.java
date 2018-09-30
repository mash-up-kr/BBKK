package org.seoul.kk.repository;

import com.querydsl.jpa.JPQLQuery;
import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.QPlayLand;
import org.seoul.kk.entity.QTraveler;
import org.seoul.kk.entity.constant.Season;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PlayLandCustomRepositoryImpl extends QuerydslRepositorySupport implements PlayLandCustomRepository {

    private static final QPlayLand playLand = QPlayLand.playLand;
    private static final QTraveler traveler = QTraveler.traveler;

    public PlayLandCustomRepositoryImpl() {
        super(PlayLand.class);
    }

    //TODO 좋아요 수가 같을 경우 정렬 기준을 추가해야합니다.
    @Override
    public List<PlayLand> findPlayLandBySeasonOrderByReviewCntLimit(Season season, long limitSize) {
        JPQLQuery<PlayLand> query = from(playLand)
                .innerJoin(playLand.traveler, traveler)
                .fetchJoin()
                .where(playLand.season.eq(season))
                .orderBy(playLand.reviewCnt.desc())
                .limit(limitSize);

        return query.fetch();
    }

    //TODO 좋아요 수가 같을 경우 정렬 기준을 추가해야합니다.
    @Override
    public List<PlayLand> findPlayLandOrderByReviewCntLimit(long limitSize) {
        JPQLQuery<PlayLand> query = from(playLand)
                .innerJoin(playLand.traveler, traveler)
                .fetchJoin()
                .orderBy(playLand.reviewCnt.desc())
                .limit(limitSize);

        return query.fetch();
    }

    @Override
    public List<PlayLand> findPlayLandOrderByCreatedAtFromCursorLimit(long cursor, long size) {
        JPQLQuery<PlayLand> query = from(playLand)
                .innerJoin(playLand.traveler, traveler)
                .fetchJoin()
                .orderBy(playLand.createdAt.desc())
                .offset(cursor)
                .limit(size);

        return query.fetch();
    }

    @Override
    public List<PlayLand> findPlayLandBySeasonOrderByCreatedAtFromCursorLimit(long cursor, long size, Season season) {
        JPQLQuery<PlayLand> query = from(playLand)
                .innerJoin(playLand.traveler, traveler)
                .fetchJoin()
                .where(playLand.season.eq(season))
                .orderBy(playLand.createdAt.desc())
                .offset(cursor)
                .limit(size);

        return query.fetch();
    }
}
