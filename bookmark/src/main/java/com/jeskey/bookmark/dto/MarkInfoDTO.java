package com.jeskey.bookmark.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarkInfoDTO {

    private Long ino;

    private Long mno;
    
    @NotEmpty(message = "도서관을 입력해주세요")
    @Size(max = 30, message = "최대 30글자까지 입력할 수 있습니다")
    private String library;

    @Size(max = 20, message = "최대 20글자까지 입력할 수 있습니다")
    private String location;

    private String type;
}
