package org.seoul.kk.repository;

import org.seoul.kk.entity.BookMark;

import java.util.List;

public interface BookMarkCustomRepository {

    List<BookMark> findBookMarkListByTravelerId(Long travelerId);
    void customDeleteById(Long id);
}
