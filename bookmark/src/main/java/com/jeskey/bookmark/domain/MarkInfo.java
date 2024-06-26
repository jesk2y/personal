package com.jeskey.bookmark.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="mark_info")
@DynamicInsert  //값을 설정하지 않은 필드를 제외하고 insert문을 실행한다.
@ToString(exclude = "mark")
public class MarkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mark mark;

    @Column(length = 30, nullable = false)
    private String library;

    @Column(length = 20)
    private String location;

    @ColumnDefault("'offline'")
    @Column(nullable = false)
    private String type;

    public void update(String library, String location, String type){
        this.library = library;
        this.location = location;
        this.type = type;
    }
}