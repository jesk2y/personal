package com.jeskey.bookmark.repository;


import com.jeskey.bookmark.domain.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Long> /* 확장 추가 */ {

/*    @Modifying
    @Query(value = "delete from Mark m where m.email=:email")
    void deleteAllByEmail(String email);	//회원탈퇴시 모든 데이터 삭제

    @Modifying
    @Query(value = "delete from Mark m where m.book.isbn=:isbn and m.email=:email")
    void deleteMarks(String email, String isbn);    //북마크 해제시 모든 도서관 정보 삭제

    @Query(value="select m from Mark m where char_length(m.library) > 0 " +
            "and m.email = :email and m.book.isbn = :isbn order by m.mno desc")
    List<Mark> getMarkList(String email, String isbn);

    boolean existsByEmailAndBookIsbn(String email, String isbn);

    @Query(value="SELECT m FROM Mark m WHERE m.email = :email and CHAR_LENGTH(m.library) > 0 "
            + "GROUP BY m.library ORDER BY m.mno desc")
    List<Mark> getLibraryList(String email);*/
}
