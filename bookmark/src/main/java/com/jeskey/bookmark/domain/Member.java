package com.jeskey.bookmark.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("deleted_at is NULL")
public class Member {

    @Id
    private String email;

    @Column(length = 8, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @ColumnDefault("'Y'")
    @Enumerated(EnumType.STRING)
    private FlagYN activated;

    @ElementCollection
    @CollectionTable(name="member_role")
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();  //추가

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime joinDate;

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void changeNickname(String nickname){
        this.nickname = nickname;
    }
}
