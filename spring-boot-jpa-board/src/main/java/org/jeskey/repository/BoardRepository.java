package org.jeskey.repository;

import java.util.Optional;

import org.jeskey.domain.Board;
import org.jeskey.repository.support.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

	@EntityGraph(attributePaths = {"fileList"})
	@Query("select b from Board b where b.bno =:bno")
	Optional<Board> findByIdWithImages(@Param("bno") Long bno);

}
