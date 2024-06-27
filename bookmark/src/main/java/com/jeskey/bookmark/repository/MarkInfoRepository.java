package com.jeskey.bookmark.repository;

import com.jeskey.bookmark.domain.MarkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkInfoRepository extends JpaRepository<MarkInfo, Long> {

    List<MarkInfo> findAllByMarkMno(Long mno);

    void deleteAllByMarkMno(Long mno);
}
