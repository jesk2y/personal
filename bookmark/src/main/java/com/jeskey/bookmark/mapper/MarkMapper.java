package com.jeskey.bookmark.mapper;

import com.jeskey.bookmark.domain.FlagYN;
import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.dto.MarkDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarkMapper{

    MarkMapper INSTANCE = Mappers.getMapper(MarkMapper.class);
    @Mapping(source = "isRead", target="isRead", qualifiedByName="charToEnum")
    @Mapping(source = "member.email", target="email")
    MarkDTO toDTO(Mark entity);

    @Mapping(source = "dto.isRead", target="isRead", qualifiedByName="enumToChar")
    @Mapping(source = "email", target="member.email")
    Mark toEntity(MarkDTO dto);

    @Named("enumToChar")
    static char enumToChar(FlagYN isRead){
        return switch (isRead) {
            case Y -> 'Y';
            case N -> 'N';
        };
    }

    @Named("charToEnum")
    static FlagYN charToEnum(char isRead){
        return switch (isRead) {
            case 'Y' -> FlagYN.Y;
            case 'N' -> FlagYN.N;
            default -> throw new IllegalStateException("Unexpected value: " + isRead);
        };
    }
}
