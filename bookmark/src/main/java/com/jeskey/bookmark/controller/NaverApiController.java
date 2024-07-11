package com.jeskey.bookmark.controller;

import com.jeskey.bookmark.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class NaverApiController {
    private static final Logger log = LogManager.getLogger(NaverApiController.class);

    private final NaverApiService naverApiService;

    @GetMapping({"/search"})
    public void search(String title, Model model, @RequestParam(defaultValue = "1") int page) {

        if (title != null) {
            try {
                title = URLEncoder.encode(title, "UTF-8");
            } catch (UnsupportedEncodingException var5) {
                throw new RuntimeException("검색어 인코딩 실패", var5);
            }

            List<Map> resultList = this.naverApiService.bookSearch(title, page);
            model.addAttribute("searchList", resultList);
        }
    }
}
