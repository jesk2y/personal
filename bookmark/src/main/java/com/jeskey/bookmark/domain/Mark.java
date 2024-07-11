package com.jeskey.bookmark.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"book","member"})
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @ManyToOne
    @JoinColumn(nullable = false)
    //@BatchSize(size = 12)
    private Book book;

    @Column(name="is_read",nullable = false)
    @Builder.Default
    private char isRead = 'N';

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @OneToMany(mappedBy = "mark", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<MarkInfo> infoList = new ArrayList<>();

    public Mark(long l) {
        this.mno = l;
    }

    public void changeRead(){
        if(this.isRead == 'N'){
            this.isRead = 'Y';
        }else if(this.isRead == 'Y'){
            this.isRead = 'N';
        }
    }
}