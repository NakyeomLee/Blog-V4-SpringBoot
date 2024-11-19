package com.example.blog.Board;

import jakarta.transaction.Transactional;
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

    public BoardResponce.UpdateFormDTO 게시글수정화면보기(int id) {
        Board board = boardRepository.findById(id); // 바로 return 불가능>DTO
        return new BoardResponce.UpdateFormDTO(board);
    }

    // 진행방향: 다같이 모든 화면을 만들고(vs코드로) > 코드 세분화함(순서대로 / 거꾸로)
    // 선생님 추천(반대로) 1.레 2.컨 3.서
    public BoardResponce.DetailDTO 게시글상세보기(int id) {
        Board board = boardRepository.findById(id); // 바로 return 불가능>DTO
        return new BoardResponce.DetailDTO(board);
    }

    @Transactional
    public void 게시글쓰기(BorderRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.getTitle(), saveDTO.getContent());
    } // commit

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    } // commit or rollback 이 됨

    @Transactional // 잘되면 commit, 예외발생해서 터지면 rollback
    public void 게시글수정하기(int id, BorderRequest.UpdateDTO updateDTO) {
        // 1.
        boardRepository.update(id, updateDTO.getTitle(), updateDTO.getContent());
    }
}