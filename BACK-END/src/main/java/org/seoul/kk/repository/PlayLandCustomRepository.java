package org.seoul.kk.repository;

import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.constant.Season;

import java.util.List;

public interface PlayLandCustomRepository {

    List<PlayLand> findPlayLandBySeasonOrderByReviewCntLimit(Season season, long limitSize);
    List<PlayLand> findPlayLandOrderByReviewCntLimit(long limitSize);
    List<PlayLand> findPlayLandOrderByCreatedAtFromCursorLimit(long cursor, long size);
    List<PlayLand> findPlayLandBySeasonOrderByCreatedAtFromCursorLimit(long cursor, long size, Season season);
}
