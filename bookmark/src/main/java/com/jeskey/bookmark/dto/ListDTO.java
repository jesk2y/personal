package com.jeskey.bookmark.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListDTO {
    String email;

    int page;
    int display = 12;

    String keyword;
    String library;
    boolean exceptRead;
}
