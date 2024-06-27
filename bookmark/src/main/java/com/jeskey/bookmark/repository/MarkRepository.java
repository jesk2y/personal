package com.jeskey.bookmark.repository;


import com.jeskey.bookmark.domain.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Long> /* 확장 추가 */ {

    void deleteAllByMemberEmail(String email);	//회원탈퇴시 모든 데이터 삭제

    boolean existsByMemberEmailAndBookIsbn(String email, String isbn);


/*
    @Query(value="SELECT m FROM Mark m WHERE m.email = :email and CHAR_LENGTH(m.library) > 0 "
            + "GROUP BY m.library ORDER BY m.mno desc")
    List<Mark> getListByLibrary(String email);*/


    /*
    @Query(value="select m from Mark m where m.member.email = :email order by m.mno desc")
    List<Mark> getMarkListByEmail(String email);
    */
}
