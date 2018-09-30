package org.seoul.kk.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.seoul.kk.SpringTestSupport;
import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.entity.constant.Season;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayLandRepositoryTest extends SpringTestSupport {

    @Autowired
    private PlayLandRepository playLandRepository;

    @Autowired
    private TravelerRepository travelerRepository;

    @BeforeEach
    void givenPlayLand() {
        Traveler traveler = mockTraveler();
        playLandRepository.save(mockPlayLand(1L, "한강 유원지", Season.SPRING, traveler));
        playLandRepository.save(mockPlayLand(2L,"잠실 종합운동장", Season.SUMMER, traveler));
        playLandRepository.save(mockPlayLand(3L,"개롱역", Season.WINTER, traveler));
        playLandRepository.save(mockPlayLand(4L,"용인", Season.AUTUMN, traveler));
        playLandRepository.save(mockPlayLand(5L,"신당동", Season.AUTUMN, traveler));
    }

    @AfterEach
    void deletePlayLand() {
        playLandRepository.deleteAll();
        travelerRepository.deleteAll();
    }

    @Test
    void findPlayLandByReportingTraveler() {
        Traveler traveler = travelerRepository.getOne(1L);
        List<PlayLand> result = playLandRepository.findAllByTraveler(traveler);

        result.forEach(item -> assertEquals(1L, item.getTraveler().getId().longValue()));
        assertEquals(5, result.size());
    }

    private PlayLand mockPlayLand(Long id, String title, Season season, Traveler traveler) {
        return PlayLand.builder()
                .id(id)
                .title(title)
                .season(season)
                .content("test content")
                .traveler(traveler)
                .position("test pos")
                .build();
    }

    private Traveler mockTraveler() {
        return Traveler.builder()
                .id(1L)
                .nickname("PROGRAMMER")
                .build();
    }

}
