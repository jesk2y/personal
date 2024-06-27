package com.jeskey.bookmark.service;

import com.jeskey.bookmark.dto.MarkInfoDTO;

import java.util.List;

public interface MarkInfoService {

    void addMarkInfo(MarkInfoDTO dto);

    void updateMarkInfo(MarkInfoDTO dto);

    List<MarkInfoDTO> getMarkInfoList(Long mno);

 //   MarkInfoDTO getMarkInfo(Long ino);

    void deleteMarkInfo(Long ino);

}
