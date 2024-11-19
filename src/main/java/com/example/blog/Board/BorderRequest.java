package com.example.blog.Board;

import lombok.Data;

public class BorderRequest {

    @Data // getter, setter, toString 등 포함됨
    public static class SaveDTO { // static 없으면 에러
        private String title;
        private String content;

        // toString 형태 -롬북 사용시 자동 출력되어 필요없다.
        // 구조만 확인하기
/*        @Override
         public String toString() {
            return "SaveDTO{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
 */
    }
}