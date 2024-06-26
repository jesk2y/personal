package com.jeskey.bookmark.repository;


import com.jeskey.bookmark.domain.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MarkRepository extends JpaRepository<Mark, Long> /* 확장 추가 */ {

 @Modifying
    @Query(value = "delete from Mark m where m.member.email=:email")
    void deleteAllByEmail(String email);	//회원탈퇴시 모든 데이터 삭제

    @Modifying
    @Query(value = "delete from MarkInfo m where m.mark.mno = :mno")
    void deleteMarkInfos(Long mno);    //북마크 해제시 모든 도서관 정보 삭제


/*
    @Query(value="SELECT m FROM Mark m WHERE m.email = :email and CHAR_LENGTH(m.library) > 0 "
            + "GROUP BY m.library ORDER BY m.mno desc")
    List<Mark> getListByLibrary(String email);*/


    /*
    @Query(value="select m from Mark m where m.member.email = :email order by m.mno desc")
    List<Mark> getMarkListByEmail(String email);
    */
}
