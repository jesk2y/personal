package com.jeskey.bookmark.service;

import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.domain.QMark;
import com.jeskey.bookmark.dto.ListDTO;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MarkRepositoryCustomImpl extends QuerydslRepositorySupport implements MarkRepositoryCustom {
    public MarkRepositoryCustomImpl(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    public Page<Mark> getList(ListDTO listDTO) {

        Pageable pageable  = PageRequest.of(listDTO.getPage()-1, listDTO.getDisplay(), Sort.by("mno").descending());

        QMark qmark = QMark.mark;
        JPQLQuery<Mark> query = from(qmark);

        query.where(qmark.book.title.contains(listDTO.getKeyword()));	//임시로 키워드 설정

        long count = query.fetch().size(); // 페이징을 적용하기 전의 전체 count를 구해야 한다.

        this.getQuerydsl().applyPagination(pageable, query);

        List<Mark> list = query.fetch();

        return new PageImpl<>(list, pageable, count);
    }
}
