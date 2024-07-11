package com.jeskey.bookmark.dto;

import com.jeskey.bookmark.domain.FlagYN;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListDTO {

    String email;

    int page = 1;

    int display = 12;

    //책 제목 검색
    String keyword;

    //특정 도서관을 선택해 책 리스트 가져오기
    String library;

    //읽지 않은 책만 가져오기
    FlagYN exceptRead = FlagYN.N;

    public void setPage(){
        if(this.page < 1){
            this.page = 1;
        }
    }

   public void setExceptRead(char c){
        if(c == 'Y'){
            this.exceptRead = FlagYN.Y;
        }
   }
}
