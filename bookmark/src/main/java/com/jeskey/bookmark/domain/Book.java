package com.jeskey.bookmark.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.BatchSize;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@BatchSize(size=20)
public class Book {

    @Id
    private String isbn;

    @Column(length = 100, nullable = false)
    private String title;

    private String imgLink;
}
