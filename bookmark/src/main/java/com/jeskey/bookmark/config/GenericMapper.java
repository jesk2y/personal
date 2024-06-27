package com.jeskey.bookmark.config;

import com.jeskey.bookmark.domain.MarkInfo;
import com.jeskey.bookmark.dto.MarkInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenericMapper {

    GenericMapper MARK_INFO_INSTANCE = Mappers.getMapper(GenericMapper.class);

    //MarkInfo 관련
    @Mapping(source = "mno", target="mark.mno")
    MarkInfo toEntity(MarkInfoDTO markInfoDTO);

    @Mapping(source = "mark.mno", target="mno")
    MarkInfoDTO toDto(MarkInfo markInfo);

    //Mark 관련

    //Book 관련

    //Member 관련
}