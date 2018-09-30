package org.seoul.kk.repository;

import org.seoul.kk.entity.relation.LikePlayLandRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePlayLandRelationRepository extends JpaRepository<LikePlayLandRelation, Long> {
}
