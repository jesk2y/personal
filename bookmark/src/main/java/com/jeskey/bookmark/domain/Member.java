package com.jeskey.bookmark.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String email;

    @Column(length = 8, nullable = false)
    private String name;

    @Column(nullable = false)
    private String pw;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();  //추가

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime joinDate;

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void changePassword(String mpw){
        this.pw = pw;
    }

    public void changeEmail(String email){
        this.email = email;
    }

}
