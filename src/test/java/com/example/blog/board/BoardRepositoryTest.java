package com.example.blog.board;

import com.example.blog.Board.Board;
import com.example.blog.Board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

// 테스트 파일은 이름끝에 Test 붙이는게 약속(컨벤션)

@Import(BoardRepository.class)
@DataJpaTest // DB 관련된 자원들을 메모리에 올림
public class BoardRepositoryTest {

    @Autowired // 의존성 주입(스프링이 자동으로 bean 찾아서 주입)
    private BoardRepository boardRepository;
    
    // 테스트 파일에서는 의존할것을 필드 선언해놔도 메소드 파라미터에 매개변수로는 안넣음
    // => 테스트는 JVM이 하기 때문에 컨트롤이 안됨

    @Test // 테스트
    public void findAll_test() { // 테스트 메소드 이름끝에 _test 붙이는게 약속
        
        // given => 생략 가능

        // when => 테스트할 메소드
        List<Board> boardList = boardRepository.findAll();
        System.out.println();
        
        // eye
        for(Board board : boardList) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("=======================");
        }
    }
}