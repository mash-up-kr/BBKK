package org.seoul.kk.service;

import org.seoul.kk.dto.bookmark.ReqBookMarkCancelDto;
import org.seoul.kk.dto.bookmark.ReqBookMarkDto;
import org.seoul.kk.entity.BookMark;

import java.util.List;

public interface BookMarkService {

    void bookmarkPlayLand(ReqBookMarkDto requestBody);
    void bookmarkCancel(ReqBookMarkCancelDto requestBody);
    List<BookMark> retreieveBookmarkList(Long travelerId);
}
