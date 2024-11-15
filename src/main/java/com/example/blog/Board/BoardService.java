package com.example.blog.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor // final이 붙어있는 필드에 대한 생성자를 만들어줌
@Service
public class BoardService {

    // BoardRepository 의존
    // final 붙이면 new가 안됨
    // => new가 안되는데 다른곳에서 어떻게 쓰는가?
    // => 생성자가 있으면 됨 (@RequiredArgsConstructor가 만듦)
    private final BoardRepository boardRepository;

    public List<BoardResponce.DTO> 게시글목록보기() {

        List<BoardResponce.DTO> dtos = new ArrayList<>();

        List<Board> boardList = boardRepository.findAll();

        for (Board board : boardList) {
            BoardResponce.DTO dto = new BoardResponce.DTO(board);
            dtos.add(dto);
        }
        return dtos;
    }
}