package com.example.blog.Board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class BoardRequest {

    @Data // getter, setter, toString 등 포함됨
    public static class SaveDTO { // static 없으면 에러
        @NotBlank
        private String title;
        @NotBlank
        private String content;

        public Board toEntity() {
            Board board = new Board(null, title, content, null);
            return board;
        }
    }

    @Data
    public static class  UpdateDTO {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }
}