package com.test.nameprojecet.dto;

import com.test.nameprojecet.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

//DTO . 폼 데이터를 받는 객체
@AllArgsConstructor
@ToString
@Data
public class Articleform {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        //폼 데이터를 담은 DTO 객체를 엔티티로 반환
        return new Article(null,title,content);
    }
    public Article updateEntity() {
        //폼 데이터를 담은 DTO 객체를 엔티티로 반환
        return new Article(id,title,content);
    }
}
