package org.jeskey.repository.support;

import org.jeskey.domain.Board;
import org.jeskey.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface BoardRepositoryCustom {
	Page<Board> getListWithPageAndSearch(PageRequestDTO pageRequestDTO);
}
