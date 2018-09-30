package org.seoul.kk.util;

import org.seoul.kk.entity.constant.Season;

public class S3KeyHelper {

    public static String generateS3ObjectKey(Long travelerId, Season season, String fileName) {
        return String.format("playLand/%d/%s/%s", travelerId, season.name(), fileName);
    }

}
