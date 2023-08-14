package com.test.nameprojecet.entity;

import jakarta.persistence.*;
import lombok.*;

//엔티티 클래스
@AllArgsConstructor
@NoArgsConstructor //기본생성자
@ToString
@Getter
@Entity
public class Article {

    @Id //엔티티의 대표값 설정
    //DB가 ID 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;
    @Column //DB 테이블의 각 열과 연결된다.
    private String title;
    @Column //DB 테이블의 각 열과 연결된다.
    private String content;



}
