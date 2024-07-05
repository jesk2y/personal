package com.jeskey.bookmark.service;

import com.jeskey.bookmark.dto.ListDTO;
import com.jeskey.bookmark.dto.MarkDTO;

import java.util.List;

public interface MarkService {

    void insertMark(MarkDTO dto);

    //MarkDTO getMark(String isbn, String email);

    void deleteMark(Long mno);

    void changeRead(Long mno);

    MarkDTO getOne(String email, String isbn);

    List<MarkDTO> getList(ListDTO listDTO);
}
