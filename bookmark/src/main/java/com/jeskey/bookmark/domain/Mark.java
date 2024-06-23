package com.jeskey.bookmark.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(length = 30, nullable = false)
    private String library;

    @Column(length = 20)
    private String location;

    @ColumnDefault("n")
    private String eBook;

    public void change(String library, String location){
        this.library = library;
        this.location = location;
    }
}
