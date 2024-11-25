package com.example.blog._core.error;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import com.example.blog._core.util.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// 이 클래스의 책임 : 에러 처리
//@ControllerAdvice // view resolver를 타서 파일 찾음
@RestControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> err400(Exception400 e) {
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST); // 400
        return rn;
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> err404(Exception404 e) {
        // ResponseEntity로 404로 바꾸지 않을 경우 200이라 문제가 생긴다.
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.NOT_FOUND); // 404
        return rn;
    }

//    @ResponseBody // 파일 찾는게 아니라 return을 할 때만 붙임
//    @ExceptionHandler(Exception400.class)
//    public String err400(Exception400 e) {
//
//        System.out.println("err400");
//
//        // """사이에는 자바스크립트 코드 작성
//        // ${msg} : 변수 msg (el 이용)
//        // history.back(); 뒤로가기
//        String body = """
//                <script>
//                    alert('${msg}');
//                    history.back();
//                </script>
//                """.replace("${msg}", e.getMessage()); // msg를 메세지로 변환
//
//        return body;
//    }
//
//    @ResponseBody // 파일 찾는게 아니라 return을 할 때만 붙임
//    @ExceptionHandler(Exception404.class)
//    public String err404(Exception404 e) {
//        System.out.println("err404");
//
//        // """사이에는 자바스크립트 코드 작성
//        // ${msg} : 변수 msg (el 이용)
//        // history.back(); 뒤로가기
//        String body = """
//                <script>
//                    alert('${msg}');
//                    history.back();
//                </script>
//                """.replace("${msg}", e.getMessage()); // msg를 메세지로 변환
//
//        return body;
//    }
}