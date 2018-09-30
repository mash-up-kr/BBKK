package org.seoul.kk.repository;

import org.seoul.kk.entity.Nickname;
import org.seoul.kk.entity.constant.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NicknameRepository extends JpaRepository<Nickname,Long> {

    List<Nickname> findAllByClassification(Classification classification);
}
