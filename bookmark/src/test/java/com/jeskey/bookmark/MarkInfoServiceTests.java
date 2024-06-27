package com.jeskey.bookmark;

import com.jeskey.bookmark.dto.MarkInfoDTO;
import com.jeskey.bookmark.service.MarkInfoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MarkInfoServiceTests {

    @Autowired
    MarkInfoService markInfoService;

    @Test
    void insert(){

        MarkInfoDTO dto = MarkInfoDTO.builder()
                    .mno(3L)
                    .location("도서관")
                    .library("222-456-789")
                    .type("online")
                    .build();

        markInfoService.addMarkInfo(dto);
    }

    @Test
    void update(){

        MarkInfoDTO dto = MarkInfoDTO.builder()
                .mno(3L)
                .ino(12L)
                .location("수정도서관")
                .library("222-456-789")
                .type("offline")
                .build();

        markInfoService.updateMarkInfo(dto);
    }

    @Test
    void getList(){
        log.info(markInfoService.getMarkInfoList(3L));
    }
}
