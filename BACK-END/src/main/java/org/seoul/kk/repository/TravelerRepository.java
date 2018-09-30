package org.seoul.kk.repository;

import org.seoul.kk.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Long> {

    Optional<Traveler> findByUuid(String uuid);
}
