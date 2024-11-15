package com.example.blog.Board;

import lombok.Data;

public class BoardResponce {

    @Data // getter,setter 다 포함돼있음
    public static class DTO {
        private int id; // 게시글 번호
        private String title; // 게시글 제목

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}