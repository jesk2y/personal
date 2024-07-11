package com.jeskey.bookmark.mapper;

import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.domain.MarkInfo;
import com.jeskey.bookmark.dto.MarkInfoDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-11T21:24:38+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class MarkInfoMapperImpl implements MarkInfoMapper {

    @Override
    public MarkInfoDTO toDTO(MarkInfo entity) {
        if ( entity == null ) {
            return null;
        }

        MarkInfoDTO.MarkInfoDTOBuilder markInfoDTO = MarkInfoDTO.builder();

        markInfoDTO.mno( entityMarkMno( entity ) );
        markInfoDTO.ino( entity.getIno() );
        markInfoDTO.library( entity.getLibrary() );
        markInfoDTO.location( entity.getLocation() );
        markInfoDTO.type( entity.getType() );

        return markInfoDTO.build();
    }

    @Override
    public MarkInfo toEntity(MarkInfoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MarkInfo.MarkInfoBuilder markInfo = MarkInfo.builder();

        markInfo.mark( markInfoDTOToMark( dto ) );
        markInfo.ino( dto.getIno() );
        markInfo.library( dto.getLibrary() );
        markInfo.location( dto.getLocation() );
        markInfo.type( dto.getType() );

        return markInfo.build();
    }

    private Long entityMarkMno(MarkInfo markInfo) {
        if ( markInfo == null ) {
            return null;
        }
        Mark mark = markInfo.getMark();
        if ( mark == null ) {
            return null;
        }
        Long mno = mark.getMno();
        if ( mno == null ) {
            return null;
        }
        return mno;
    }

    protected Mark markInfoDTOToMark(MarkInfoDTO markInfoDTO) {
        if ( markInfoDTO == null ) {
            return null;
        }

        Mark.MarkBuilder mark = Mark.builder();

        mark.mno( markInfoDTO.getMno() );

        return mark.build();
    }
}
