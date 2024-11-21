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

        // 스트림은 일종의 for문이라고 이해하면됨
        // 여러개의 데이터를 한번에 처리해야될때 스트림을 씀
        return boardRepository.findAll().stream()
                .map(BoardResponce.DTO::new)
                .toList();
    }

    public BoardResponce.UpdateFormDTO 게시글수정화면보기(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다. : " + id));

        return new BoardResponce.UpdateFormDTO(board);
    }

    // 진행방향: 다같이 모든 화면을 만들고(vs코드로) > 코드 세분화함(순서대로 / 거꾸로)
    // 선생님 추천(반대로) 1.레 2.컨 3.서
    public BoardResponce.DetailDTO 게시글상세보기(int id) {
        // .orElseThrow : 예외처리를 정확하게 할 수있게 길 안내(명시적으로 안내)
        // 데이터 무결성을 최대화하고 변수를 최소화하기위해서
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다. : " + id));
        return new BoardResponce.DetailDTO(board);
    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.toEntity());
    } // commit

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    } // commit or rollback 이 됨

    @Transactional // 잘되면 commit, 예외발생해서 터지면 rollback
    public void 게시글수정하기(int id, BoardRequest.UpdateDTO updateDTO) {
        // 게시글 조회
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다. : " + id));
        
        board.update(updateDTO.getTitle(), updateDTO.getContent());
        // 영속화된 객체 상태 변경 - update + commit => 더티 체킹
    }
}