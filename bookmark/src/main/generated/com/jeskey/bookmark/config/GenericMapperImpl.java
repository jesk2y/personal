package com.jeskey.bookmark.config;

import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.domain.MarkInfo;
import com.jeskey.bookmark.dto.MarkInfoDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T20:34:01+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class GenericMapperImpl implements GenericMapper {

    @Override
    public MarkInfo toEntity(MarkInfoDTO markInfoDTO) {
        if ( markInfoDTO == null ) {
            return null;
        }

        MarkInfo.MarkInfoBuilder markInfo = MarkInfo.builder();

        markInfo.mark( markInfoDTOToMark( markInfoDTO ) );
        markInfo.ino( markInfoDTO.getIno() );
        markInfo.library( markInfoDTO.getLibrary() );
        markInfo.location( markInfoDTO.getLocation() );
        markInfo.type( markInfoDTO.getType() );

        return markInfo.build();
    }

    @Override
    public MarkInfoDTO toDto(MarkInfo markInfo) {
        if ( markInfo == null ) {
            return null;
        }

        MarkInfoDTO.MarkInfoDTOBuilder markInfoDTO = MarkInfoDTO.builder();

        markInfoDTO.mno( markInfoMarkMno( markInfo ) );
        markInfoDTO.ino( markInfo.getIno() );
        markInfoDTO.library( markInfo.getLibrary() );
        markInfoDTO.location( markInfo.getLocation() );
        markInfoDTO.type( markInfo.getType() );

        return markInfoDTO.build();
    }

    protected Mark markInfoDTOToMark(MarkInfoDTO markInfoDTO) {
        if ( markInfoDTO == null ) {
            return null;
        }

        Mark.MarkBuilder mark = Mark.builder();

        mark.mno( markInfoDTO.getMno() );

        return mark.build();
    }

    private Long markInfoMarkMno(MarkInfo markInfo) {
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
}
