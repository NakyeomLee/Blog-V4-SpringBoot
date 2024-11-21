package com.example.blog._core.util;

import com.example.blog.Board.Board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Encoding {

    // Board 객체 받아 createdAt를 "yyyy.mm.dd" 형태 반환하는 메서드
    public static String formatToStr(Board board) {
        // Board 객체에서 createdAt을 가져와서 포맷
        Date createdAt = board.getCreatedAt();
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        return date.format(createdAt); // 포맷된 날짜 문자열 반환
    }
}