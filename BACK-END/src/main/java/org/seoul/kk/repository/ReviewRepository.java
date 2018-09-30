package org.seoul.kk.repository;

import org.seoul.kk.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {

    Optional<Review> findByIdAndTravelerId(Long id, Long travelerId);
}
