package com.jeskey.bookmark.mapper;

import com.jeskey.bookmark.domain.MarkInfo;
import com.jeskey.bookmark.dto.MarkInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MarkInfoMapper{
    MarkInfoMapper instance = Mappers.getMapper(MarkInfoMapper.class);

    @Mapping(source = "mark.mno", target="mno")
    MarkInfoDTO toDTO(MarkInfo entity);

    @Mapping(source = "mno", target="mark.mno")
    MarkInfo toEntity(MarkInfoDTO dto);

}
