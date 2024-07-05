package com.jeskey.bookmark.dto;

import com.jeskey.bookmark.domain.FlagYN;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {


    private String email;

    private String nickname;

    private String password;

    private FlagYN activated;

    private LocalDateTime joinDate;

    public void setEmail(String email){
        this.email = email;
    }
}
