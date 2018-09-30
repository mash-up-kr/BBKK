package org.seoul.kk.repository;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.seoul.kk.entity.BookMark;
import org.seoul.kk.entity.QBookMark;
import org.seoul.kk.entity.QPlayLand;
import org.seoul.kk.entity.QTraveler;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookMarkCustomRepositoryImpl extends QuerydslRepositorySupport implements BookMarkCustomRepository {

    private static final QBookMark bookMark = QBookMark.bookMark;
    private static final QTraveler traveler = QTraveler.traveler;
    private static final QPlayLand playLand = QPlayLand.playLand;

    @PersistenceContext
    private EntityManager entityManager;

    public BookMarkCustomRepositoryImpl() {
        super(BookMark.class);
    }

    @Override
    public List<BookMark> findBookMarkListByTravelerId(Long travelerId) {
        JPQLQuery<BookMark> query = from(bookMark)
                .innerJoin(bookMark.playLand, playLand)
                .fetchJoin()
                .innerJoin(playLand.traveler, traveler)
                .fetchJoin()
                .where(bookMark.traveler.id.eq(travelerId));

        return query.fetch();
    }

    @Transactional
    @Override
    public void customDeleteById(Long id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        queryFactory.delete(bookMark)
                .where(bookMark.id.eq(id))
                .execute();
    }
}
