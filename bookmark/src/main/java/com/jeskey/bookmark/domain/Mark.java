package com.jeskey.bookmark.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert  //값을 설정하지 않은 필드를 제외하고 insert문을 실행한다.
@ToString(exclude = {"book","member"})
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Book book;

    @ColumnDefault("'N'")
    @Column(name="is_read",nullable = false)
    @Enumerated(EnumType.STRING)
    private FlagYN isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @OneToMany(mappedBy = "mark", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<MarkInfo> infoList = new ArrayList<>();

    public Mark(Long mno){
        this.mno = mno;
    }
}