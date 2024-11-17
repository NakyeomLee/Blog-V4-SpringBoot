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

        // BoardResponce 클래스의 static 클래스 DTO라서 BoardResponce.DTO로 가져올 수 있음
        List<BoardResponce.DTO> dtos = new ArrayList<>();

        List<Board> boardList = boardRepository.findAll();

        // boardList를 순회해서 DTO 클래스의 필드(필요한 정보만 선언해둠)를 dtos 리스트에 add
        // => 깊은 복사
        for (Board board : boardList) {
            BoardResponce.DTO dto = new BoardResponce.DTO(board);
            dtos.add(dto);
        }
        return dtos;
    }

    public BoardResponce.DetailsDTO 게시글상세보기(int id) {

        Board board = boardRepository.findById(id);

        return new BoardResponce.DetailsDTO(board);
    }
}