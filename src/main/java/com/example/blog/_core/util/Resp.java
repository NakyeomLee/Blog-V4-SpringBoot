package com.example.blog._core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resp<T> {
    private Boolean success; // 성공, 실패 구분
    private String msg;
    private T body; // new 할 때 타입이 정해진다.

    public static <T> Resp<T> ok(T body) {
        // 반환형은 Resp<T>, 앞의 <T>는 매개변수 타입(T)과 일치해야 하는 문법
        // 메서드 실행 시에 클래스의 타입을 정한다.
        return new Resp<>(true, "성공", body);
    }

    public static <T> Resp<T> fail(String msg) {
        return new Resp<>(false, msg, null);
    }
}
