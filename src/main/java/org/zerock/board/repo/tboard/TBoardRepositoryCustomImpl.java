package org.zerock.board.repo.tboard;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.zerock.board.domain.QTBoard;
import org.zerock.board.domain.QTReply;
import org.zerock.board.domain.TBoard;
import org.zerock.board.dto.SearchDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TBoardRepositoryCustomImpl extends QuerydslRepositorySupport implements TBoardRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public TBoardRepositoryCustomImpl() {
        super(TBoard.class);
    }

    @Override
    public List<Object[]> getCustomList(SearchDTO dto, Pageable pageable) {

        log.info("custom impl class run................");

        QTBoard board = QTBoard.tBoard;
        QTReply reply = QTReply.tReply;

        JPQLQuery<TBoard> jpqlQuery = from(board);
        jpqlQuery.leftJoin(board.replyList, reply);

        JPQLQuery<Tuple> tuple =
                jpqlQuery.select(board.bno, board.title, reply.count() ).groupBy(board.bno);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());
        tuple.orderBy(board.bno.desc());

        log.info("" + tuple);

        List<Tuple> list = tuple.fetch();

//        for (Tuple tuple1 : list) {
//            log.info("------------------------");
//            log.info("" + tuple1);
//        }

        long total = tuple.fetchCount();

        List<Object[]> arrList = list.stream().map(t -> t.toArray()).collect(Collectors.toList());

        return arrList;
    }



    
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Object[]> getCustomList(SearchDTO dto, Pageable pageable) {
//
//        log.info("getCustomList................");
//
//        StringBuilder query =  new StringBuilder("select b.bno, b.title, count(r.rno) from TBoard b LEFT JOIN b.replyList r GROUP BY b ");
//
//        query.append("Order by ");
//
//        Sort.Order order = pageable.getSort().getOrderFor("bno");
//
//        query.append(order.getProperty() + " " + order.getDirection() );
//
//        Query query1 = entityManager.createQuery(query.toString());
//
//        query1.setFirstResult(pageable.getPageNumber());
//        query1.setMaxResults(pageable.getPageSize());
//
//        return query1.getResultList();
//    }
}
