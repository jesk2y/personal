package org.jeskey.repository;

import org.jeskey.domain.Board;
import org.jeskey.repository.support.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

}
