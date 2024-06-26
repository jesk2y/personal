package com.jeskey.bookmark;


import com.jeskey.bookmark.domain.Book;
import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.domain.MarkInfo;
import com.jeskey.bookmark.domain.Member;
import com.jeskey.bookmark.repository.BookRepository;
import com.jeskey.bookmark.repository.MarkInfoRepository;
import com.jeskey.bookmark.repository.MarkRepository;
import com.jeskey.bookmark.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class MarkRepositoryTests {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    MarkInfoRepository markInfoRepository;

    @Autowired
    MemberRepository memberRepository;

    Member member = Member.builder()
            .email("user1@naver.com")
            .nickname("유저")
            .password("123123").build();

    Book book = Book.builder()
            .isbn("9788937460449")
            .title("데미안")
            .imgLink("https://shopping-phinf.pstatic.net/main_3244164/32441645060.20230620100615.jpg?type=w300").build();

    @Test
    public void insertBook(){

        bookRepository.save(book);
    }

    @Test
    public void insertMember(){
        Member member = Member.builder()
                .email("user1@naver.com")
                .nickname("유저")
                .password("123123").build();

        memberRepository.save(member);
    }

    @Test
    public void insertMark(){
        Mark mark = Mark.builder()
                .member(member)
                .book(book)
                .build();

        markRepository.save(mark);
    }

    @Test
    public void insertMarkInfo(){
        MarkInfo markInfo = MarkInfo.builder()
                .mark(new Mark(1L))
                .location("강남도서관")
                .library("222-456-789")
                .type("online")
                .build();

        markInfoRepository.save(markInfo);
    }

    @Test
    @Transactional
    @Commit
    public void updateMarkInfo(){
        Optional<MarkInfo> result = markInfoRepository.findById(1L);

        MarkInfo markInfo = result.orElseThrow();

        markInfo.update("강남도서관","333-333-333-333", "online");
    }

/*    @Test
    @Transactional
    public void getMarkList(){

        markRepository.getMarkListByEmail("user1@naver.com").forEach(vo -> log.info(vo.getBook()));
    }*/

    @Test
    public void getMarkInfo(){

        log.info(markInfoRepository.findById(1L));
    }

    @Test
    public void deleteMark(){

        markRepository.deleteById(1L);
    }
}
