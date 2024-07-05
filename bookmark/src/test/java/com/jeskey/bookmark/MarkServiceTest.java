package com.jeskey.bookmark;


import com.jeskey.bookmark.domain.Book;
import com.jeskey.bookmark.domain.Member;
import com.jeskey.bookmark.dto.MarkDTO;
import com.jeskey.bookmark.repository.BookRepository;
import com.jeskey.bookmark.service.MarkService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MarkServiceTest {

    @Autowired
    MarkService markService;

    @Autowired
    BookRepository bookRepository;

    Book book = Book.builder()
            .isbn("9788954609173")
            .title("위대한 개츠비")
            .imgLink("https://shopping-phinf.pstatic.net/main_3247364/32473649926.20231004072344.jpg?type=w300").build();

    Member member = Member.builder()
            .email("user1@naver.com")
            .nickname("유저")
            .password("123123").build();
/*    @Test
    public void deleteMark(){

        markRepository.deleteById(1L);
    }*/

    @Test
    public void saveMark(){

        MarkDTO dto = MarkDTO.builder()
                .book(book)
                .email("user1@naver.com").build();

        markService.insertMark(dto);
    }

    @Test
    public void changeRead(){

        markService.changeRead(3L);

    }

    @Test
    public void getOne(){
        log.info(markService.getOne("user1@naver.com", "9788954609173"));
    }

    @Test
    public void getList(){

        
    }
}
