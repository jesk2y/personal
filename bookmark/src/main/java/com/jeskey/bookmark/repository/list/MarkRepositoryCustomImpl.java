package com.jeskey.bookmark.repository.list;

import com.jeskey.bookmark.domain.FlagYN;
import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.domain.QMark;
import com.jeskey.bookmark.domain.QMarkInfo;
import com.jeskey.bookmark.dto.ListDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.thymeleaf.util.StringUtils;

import java.util.List;

public class MarkRepositoryCustomImpl extends QuerydslRepositorySupport implements MarkRepositoryCustom {
    public MarkRepositoryCustomImpl() {

        super(Mark.class);  //반드시 Mark.class로 설정해야함
    }

    QMark qmark = QMark.mark;
    QMarkInfo qMarkInfo = QMarkInfo.markInfo;

    @Override
    public List<Mark> getList(ListDTO listDTO) {

        Pageable pageable  = PageRequest.of(listDTO.getPage()-1, listDTO.getDisplay(), Sort.by("mno").descending());

        List<Mark> list;

        JPQLQuery<Mark> query = from(qmark);

        list = innerJoinLibrary(query, listDTO.getLibrary())
                .where(containTitle(listDTO.getKeyword())
                ,eqLibrary(listDTO.getLibrary())
                ,exceptRead(listDTO.getExceptRead()))
                .orderBy(qmark.mno.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    /*    //페이징
        long count = query.fetch().size(); // 페이징을 적용하기 전의 전체 count를 구해야 한다.

        this.getQuerydsl().applyPagination(pageable, query);

        query.fetch();*/

        return list;
    }

    //책 제목 검색 적용
    private BooleanExpression containTitle(String title){

        return StringUtils.isEmpty(title) ? null : qmark.book.title.contains(title);
    }

    //동적 markInfo 조인
    private JPQLQuery<Mark> innerJoinLibrary(JPQLQuery<Mark> query, String library){

        return StringUtils.isEmpty(library) ? query : query.innerJoin(qmark.infoList, qMarkInfo);
    }

    //도서관 조건절 적용
    private BooleanExpression eqLibrary(String library){

        return StringUtils.isEmpty(library) ? null : qmark.book.title.contains(library);
    }

    /*
    * 안읽은 책만 가져올지 여부
    * N : 제외하지 않음 -> 모든 책 가져옴, 조건절 x
    * Y : 읽은 책 제외함 -> 읽지 않은 책만 가져옴, 조건절 'N' 적용
    * */
    private BooleanExpression exceptRead(FlagYN read){

        return read == FlagYN.N ? null : qmark.isRead.eq('N');
    }
}
