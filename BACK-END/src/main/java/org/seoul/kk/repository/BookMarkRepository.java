package org.seoul.kk.repository;

import org.seoul.kk.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Long>, BookMarkCustomRepository {
}
