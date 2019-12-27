package org.zerock.board.repo.tboard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.board.domain.TBoard;
import org.zerock.board.dto.SearchDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Slf4j
public class TBoardRepositoryCustomImpl implements TBoardRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> getCustomList(SearchDTO dto, Pageable pageable) {

        log.info("getCustomList................");

        StringBuilder query =  new StringBuilder("select b.bno, b.title, count(r.rno) from TBoard b LEFT JOIN b.replyList r GROUP BY b ");

        query.append("Order by ");

        Sort.Order order = pageable.getSort().getOrderFor("bno");

        query.append(order.getProperty() + " " + order.getDirection() );

        Query query1 = entityManager.createQuery(query.toString());

        query1.setFirstResult(pageable.getPageNumber());
        query1.setMaxResults(pageable.getPageSize());

        return query1.getResultList();
    }
}
