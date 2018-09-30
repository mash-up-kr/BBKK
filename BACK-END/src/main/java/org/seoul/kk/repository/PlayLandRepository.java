package org.seoul.kk.repository;

import org.seoul.kk.entity.PlayLand;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.entity.constant.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayLandRepository extends JpaRepository<PlayLand, Long>, PlayLandCustomRepository {

    List<PlayLand> findAllByTraveler(Traveler traveler);
    long countBySeason(Season season);
}
