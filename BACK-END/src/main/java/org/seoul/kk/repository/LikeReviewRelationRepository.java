package org.seoul.kk.repository;

import org.seoul.kk.entity.relation.LikeReviewRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeReviewRelationRepository extends JpaRepository<LikeReviewRelation, Long> {
}
