package org.seoul.kk.service;

import lombok.RequiredArgsConstructor;
import org.seoul.kk.dto.bookmark.ReqBookMarkCancelDto;
import org.seoul.kk.dto.bookmark.ReqBookMarkDto;
import org.seoul.kk.entity.BookMark;
import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.exception.NotFoundPlayLand;
import org.seoul.kk.exception.NotFoundTraveler;
import org.seoul.kk.repository.BookMarkRepository;
import org.seoul.kk.repository.PlayLandRepository;
import org.seoul.kk.repository.TravelerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMarkServiceImpl implements BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final PlayLandRepository playLandRepository;
    private final TravelerRepository travelerRepository;

    @Override
    public void bookmarkPlayLand(ReqBookMarkDto requestBody) {
        Traveler traveler = travelerRepository.findById(requestBody.getTravelerId()).orElseThrow(NotFoundTraveler::new);
        PlayLand playLand = playLandRepository.findById(requestBody.getPlayLandId()).orElseThrow(NotFoundPlayLand::new);

        BookMark bookMark = BookMark.builder()
                .traveler(traveler)
                .playLand(playLand)
                .build();

        bookMarkRepository.save(bookMark);
    }

    @Override
    public void bookmarkCancel(ReqBookMarkCancelDto requestBody) {
        bookMarkRepository.customDeleteById(requestBody.getBookmarkId());
    }

    @Override
    public List<BookMark> retreieveBookmarkList(Long travelerId) {
        travelerRepository.findById(travelerId).orElseThrow(NotFoundTraveler::new);

        return bookMarkRepository.findBookMarkListByTravelerId(travelerId);
    }
}
