package com.example.blog._core.error;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 이 클래스의 책임 : 에러 처리
@ControllerAdvice // view resolver를 타서 파일 찾음
public class MyControllerAdvice {

    @ResponseBody // 파일 찾는게 아니라 return을 할 때만 붙임
    @ExceptionHandler(Exception400.class)
    public String err400(Exception400 e) {
        System.out.println("err400");
        
        // """사이에는 자바스크립트 코드 작성
        // ${msg} : 변수 msg (el 이용)
        // history.back(); 뒤로가기
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", e.getMessage()); // msg를 메세지로 변환
        
        return body;
    }

    @ResponseBody // 파일 찾는게 아니라 return을 할 때만 붙임
    @ExceptionHandler(Exception404.class)
    public String err404(Exception404 e) {
        System.out.println("err404");

        // """사이에는 자바스크립트 코드 작성
        // ${msg} : 변수 msg (el 이용)
        // history.back(); 뒤로가기
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", e.getMessage()); // msg를 메세지로 변환

        return body;
    }
}