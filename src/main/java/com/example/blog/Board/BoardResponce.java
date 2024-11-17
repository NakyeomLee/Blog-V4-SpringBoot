package com.example.blog.Board;

import lombok.Data;

import java.sql.Timestamp;

public class BoardResponce {

    // static이라서 바로 new 할 수 있음 => BoardResponce.DTO
    @Data // getter,setter 다 포함돼있음
    public static class DTO {
        private int id; // 게시글 번호
        private String title; // 게시글 제목

        // 생성자
        // 깊은 복사를 위해 Board를 매개 변수로 이용 => Board의 모든 것을 들고옴
        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    @Data
    public static class DetailsDTO {
        private String title; // 게시글 제목
        private String content; // 게시글 내용
        private Timestamp createdAt; // 작성 날짜

        // 생성자
        public DetailsDTO(Board board) {
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = board.getCreatedAt();
        }
    }
}