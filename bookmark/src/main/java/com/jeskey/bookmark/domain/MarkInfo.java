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
@Table(name="mark_info")
public class MarkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Mark mark;

    @Column(length = 30, nullable = false)
    private String library;

    @Column(length = 20)
    private String location;

    @ColumnDefault("'offline'")
    @Column(nullable = false)
    private String type;

    public void changeInfo(String library, String location, String type){
        this.library = library;
        this.location = location;
        this.type = type;
    }
}