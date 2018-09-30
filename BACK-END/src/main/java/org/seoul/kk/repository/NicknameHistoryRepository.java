package org.seoul.kk.repository;

import org.seoul.kk.entity.history.NicknameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NicknameHistoryRepository extends JpaRepository<NicknameHistory, Long> {

    List<NicknameHistory> findByAdjective(String adjective);
}
