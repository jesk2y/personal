package com.jeskey.bookmark;


import com.jeskey.bookmark.domain.Book;
import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.domain.Member;
import com.jeskey.bookmark.repository.BookRepository;
import com.jeskey.bookmark.repository.MarkRepository;
import com.jeskey.bookmark.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MarkRepositoryTests {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    MemberRepository memberRepository;

    Member member = Member.builder()
            .email("user1@naver.com")
            .nickname("유저")
            .password("123123").build();

    @Test
    public void insertBook(){
        Book book = Book.builder()
                .isbn("9788937460050")
                .title("동물농장")
                .imgLink("https://shopping-phinf.pstatic.net/main_3243818/32438186724.20230313185126.jpg?type=w300").build();

        bookRepository.save(book);
    }

    @Test
    public void insertMark(){
        Mark mark = Mark.builder()
                .member(member)
                .book(new Book("9788937460050"))
                .build();

        markRepository.save(mark);
    }
}
