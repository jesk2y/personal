package com.jeskey.bookmark.service;

import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.dto.ListDTO;
import org.springframework.data.domain.Page;

public interface MarkRepositoryCustom {
    Page<Mark> getList(ListDTO listDTO);
}
