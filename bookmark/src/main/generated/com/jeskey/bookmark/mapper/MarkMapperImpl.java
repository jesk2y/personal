package com.jeskey.bookmark.mapper;

import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.domain.Member;
import com.jeskey.bookmark.dto.MarkDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-11T21:24:38+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.6 (Oracle Corporation)"
)
public class MarkMapperImpl implements MarkMapper {

    @Override
    public MarkDTO toDTO(Mark entity) {
        if ( entity == null ) {
            return null;
        }

        MarkDTO.MarkDTOBuilder markDTO = MarkDTO.builder();

        markDTO.isRead( MarkMapper.charToEnum( entity.getIsRead() ) );
        markDTO.email( entityMemberEmail( entity ) );
        markDTO.mno( entity.getMno() );
        markDTO.book( entity.getBook() );

        return markDTO.build();
    }

    @Override
    public Mark toEntity(MarkDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Mark.MarkBuilder mark = Mark.builder();

        mark.member( markDTOToMember( dto ) );
        mark.isRead( MarkMapper.enumToChar( dto.getIsRead() ) );
        mark.mno( dto.getMno() );
        mark.book( dto.getBook() );

        return mark.build();
    }

    private String entityMemberEmail(Mark mark) {
        if ( mark == null ) {
            return null;
        }
        Member member = mark.getMember();
        if ( member == null ) {
            return null;
        }
        String email = member.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    protected Member markDTOToMember(MarkDTO markDTO) {
        if ( markDTO == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.email( markDTO.getEmail() );

        return member.build();
    }
}
