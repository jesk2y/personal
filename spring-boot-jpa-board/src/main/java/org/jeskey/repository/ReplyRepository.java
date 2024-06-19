package org.jeskey.repository;

import org.jeskey.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long>  {

	int countByBoardBno(Long bno);

	Page<Reply> findAllByBoardBno(Long bno, Pageable pageable);

	void deleteAllByBoardBno(Long bno);
}
