package com.jeskey.bookmark.dto;

import com.jeskey.bookmark.domain.Book;
import com.jeskey.bookmark.domain.FlagYN;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarkDTO {

    private Long mno;

    @NotEmpty
    private Book book;

    @Builder.Default
    private FlagYN isRead = FlagYN.N;

    @NotEmpty
    private String email;

    public void setEmail(String email){
        this.email = email;
    }
}
