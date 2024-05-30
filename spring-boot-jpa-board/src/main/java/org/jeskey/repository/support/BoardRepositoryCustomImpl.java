package org.jeskey.repository.support;

import java.util.List;

import org.jeskey.domain.Board;
import org.jeskey.domain.QBoard;
import org.jeskey.dto.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom{

	public BoardRepositoryCustomImpl() {
		super(Board.class);
	}

	@Override
	public Page<Board> getListWithPageAndSearch(PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable();
		String[] targets = pageRequestDTO.getTargets();
		String keyword = pageRequestDTO.getKeyword();

		QBoard qboard = QBoard.board;
		JPQLQuery<Board> query = from(qboard);

		if((targets != null && targets.length > 0) && keyword != null){
	        BooleanBuilder booleanBuilder = new BooleanBuilder();
	        for(String target : targets){
	            switch(target){
	                case "t":
	                    booleanBuilder.or(qboard.title.contains(keyword));
	                    break;
	                case "c":
	                    booleanBuilder.or(qboard.content.contains(keyword));
	                    break;
	            }
	        }
	        query.where(booleanBuilder);
	    }

		long count = query.fetch().size(); // 페이징을 적용하기 전의 전체 count를 구해야 한다

		this.getQuerydsl().applyPagination(pageable, query);

		List<Board> list = query.fetch();

		//long count = query.fetchCount(); fetchCount() 함수는 현재 권장되지 않는다. fetch().size()로 대체하는 것이 좋다

		return new PageImpl<>(list, pageable, count);
	}
}
