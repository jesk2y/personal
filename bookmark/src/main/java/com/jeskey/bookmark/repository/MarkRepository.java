package com.jeskey.bookmark.repository;


import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.repository.list.MarkRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MarkRepository extends JpaRepository<Mark, Long>, MarkRepositoryCustom /* 확장 추가 */ {

    //회원탈퇴시 모든 데이터 삭제
    void deleteAllByMemberEmail(String email);

    //Book 컨텐츠 페이지에서 마크 정보 가져오기
    Mark findByMemberEmailAndBookIsbn(String email, String isbn);

    @Query("delete from MarkInfo m where m.mark.mno = :mno")
    void deleteMarkInfosByMno(Long mno);

    Page<Mark> findByMemberEmail(String email, Pageable pageable);

    boolean existsByMemberEmailAndBookIsbn(String email, String isbn);    //북마크되어있는지 여부 확인

/*
    @Query(value="SELECT m FROM Mark m WHERE m.email = :email and CHAR_LENGTH(m.library) > 0 "
            + "GROUP BY m.library ORDER BY m.mno desc")
    List<Mark> getListByLibrary(String email);*/


    /*
    @Query(value="select m from Mark m where m.member.email = :email order by m.mno desc")
    List<Mark> getMarkListByEmail(String email);
    */
}
