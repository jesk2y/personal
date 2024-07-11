package com.jeskey.bookmark.repository.list;

import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.dto.ListDTO;

import java.util.List;

public interface MarkRepositoryCustom {
    List<Mark> getList(ListDTO listDTO);
}
