package org.zerock.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.board.domain.TBoard;
import org.zerock.board.repo.tboard.TBoardRepositoryCustom;

public interface TBoardRepository extends JpaRepository<TBoard, Integer>, TBoardRepositoryCustom {
}
