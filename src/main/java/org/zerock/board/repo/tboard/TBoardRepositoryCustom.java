package org.zerock.board.repo.tboard;

import org.springframework.data.domain.Pageable;
import org.zerock.board.dto.SearchDTO;

import java.util.List;

public interface TBoardRepositoryCustom<T> {

    public List<Object[]> getCustomList(SearchDTO dto, Pageable pageable);
}
