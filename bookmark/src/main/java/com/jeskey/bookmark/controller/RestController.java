package com.jeskey.bookmark.controller;

import com.jeskey.bookmark.dto.MarkDTO;
import com.jeskey.bookmark.service.MarkInfoService;
import com.jeskey.bookmark.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {

    private final MarkService markService;
    private final MarkInfoService markInfoService;

    //북마크 등록
    @PostMapping(value="/mark/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long register(@RequestBody MarkDTO markDTO){

        markDTO.setEmail("user1@naver.com");

        System.out.println(markDTO);    //MarkDTO 받을때 Builder 자동 됨

        return markService.insertMark(markDTO);
    }

    @GetMapping(value="/mark/check/{isbn}")
    public boolean checkMark(@PathVariable("isbn") String isbn){
        //문자열 "true""false"로 넘겨도 자바스크립트에선 boolean으로 받음

        String email = "user1@naver.com";

        return markService.checkMark(email, isbn);
    }

}
