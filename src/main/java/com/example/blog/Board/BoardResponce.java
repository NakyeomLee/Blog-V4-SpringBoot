package com.example.blog.Board;

import com.example.blog._core.util.Encoding;
import lombok.Data;

// 여러 개의 DTO 객체를 관리하기 위해 BoardResponse 클래스 작성
public class BoardResponce {

    //1118 수정
    @Data
    public static class UpdateFormDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = Encoding.formatToStr(board);
        }
    }

    //1118 삭제
    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt; // Timestamp인데 string으로 해도 된다.

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            // Encoding클래스의 날짜변환 메서드 가져와 적용
            this.createdAt = Encoding.formatToStr(board);
        }
    }

    // 1118 쓰기
    @Data
    public static class SaveDTO {
        private int id;
        private String title;
        private String content;

        public SaveDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }


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

    @Data // Getter, Setter 필요
    public static class ReadDTO {
        // BoardResponse.DTO 로 쓰기 위해 static으로 올리는 것
        private int id;
        private String title;

        public ReadDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}