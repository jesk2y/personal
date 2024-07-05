package com.jeskey.bookmark.controller;

import com.jeskey.bookmark.dto.MarkDTO;
import com.jeskey.bookmark.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/mark")
public class RestController {
    private final MarkService markService;

    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody MarkDTO markDTO){

        markDTO.setEmail("user1@naver.com");

        System.out.println(markDTO);    //MarkDTO 받을때 Builder 자동 됨
        markService.insertMark(markDTO);
    }
}
