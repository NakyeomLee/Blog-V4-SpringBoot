package com.example.blog.Board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class  UpdateDTO {
        private String title;
        private String content;
    }

    @Data // getter, setter, toString 등 포함됨
    public static class SaveDTO { // static 없으면 에러
        private String title;
        private String content;

        public Board toEntity() {
            Board board = new Board(null, title, content, null);
            return board;
        }
    }
}