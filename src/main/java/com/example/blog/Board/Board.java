package com.example.blog.Board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@AllArgsConstructor // 풀 생성자
@NoArgsConstructor // 빈(기본,디폴트) 생성자 (롬복 사용), DB에서 조회해서 가져온 RS(ResultSet)를 디폴트 생성자를 호출해서 new하고 값을 채워줌
@Getter // getter (롬복 사용)
@Table(name = "board_tb") // 해당 엔티티가 매핑되는 테이블 이름 지정
@Entity // 이 클래스를 엔티티(모델)로 지정, DB테이블에 매핑될 수 있게 함
public class Board {
    @Id // PK 설정 (이 경우 id 컬럼이 PK(기본키))
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    private Integer id; // 게시글 번호 , null일 경우를 대비해서 Integer
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private Timestamp createdAt; // 작성 날짜
}