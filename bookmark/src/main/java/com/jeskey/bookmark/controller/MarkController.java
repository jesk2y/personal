package com.jeskey.bookmark.controller;

import com.jeskey.bookmark.dto.ListDTO;
import com.jeskey.bookmark.dto.MarkDTO;
import com.jeskey.bookmark.service.MarkService;
import com.jeskey.bookmark.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MarkController {

    private final MarkService markService;
    private final NaverApiService naverApiService;

    @GetMapping("/list")
    public void getList(ListDTO listDTO, Model model){

        listDTO.setEmail("user1@naver.com");

        List<MarkDTO> list = markService.getList(listDTO);

        model.addAttribute("responseDTO", list);
    }

    @GetMapping({"/content"})
    public void getOne(String isbn, Model model) {
        Map resultMap = naverApiService.bookSearch(isbn, 1).get(0);
        model.addAttribute("book", resultMap);
    }

  /*  //비동기 메소드
    @GetMapping("/mark/list")
    public String getMarkList(String isbn, Model model, Principal principal){

        String email = principal.getName();

        model.addAttribute("markList", markService.getMarkList(email, isbn));

        return "content :: #markList";
    }

    @GetMapping(value="/listGet")
    @ResponseBody
    public List<MarkDTO> getList(String library, String keyword, Pageable pageable
            , Principal principal){

        String email = principal.getName();

        List<MarkDTO> list = markService.getListByEmail(email,keyword,library,pageable);

        return list;
    }*/
}
