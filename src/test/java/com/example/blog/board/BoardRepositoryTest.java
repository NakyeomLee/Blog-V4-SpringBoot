package com.example.blog.board;

import com.example.blog.Board.Board;
import com.example.blog.Board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

// 테스트 파일은 이름끝에 Test 붙이는게 약속(컨벤션)

@Import(BoardRepository.class) // BoardRepository 클래스를 import해서 띄움
@DataJpaTest // DB 관련된 자원들을 메모리(IOC)에 올림
public class BoardRepositoryTest {

    // 테스트 클래스는 new가 안됨 (테스트용이라서 그냥 안되도록 해놓음)
    // => 생성자로 의존할 것을 가져와서 의존성 주입하는게 안됨
    // => @Autowired 이용해서 의존성 주입 (DI, Dependency Injection)
    // BoardRepositoty 클래스를 의존하기 때문에 그 안에 있는 메소드들은 다 테스트 가능
    @Autowired // 의존성 주입(스프링이 자동으로 bean 찾아서 주입)
    private BoardRepository boardRepository;

    @Test
    public void delete_test() {
        // given
        int id = 1;

        // when
        boardRepository.delete(id);

        // eye
        List<Board> boardList = boardRepository.findAll();
        System.out.println("size: " + boardList.size());
    }

    @Test
    public void save_test() {
        // given
        String title = "제목6";
        String content = "내용6";

        // when
        boardRepository.save(title, content);

        // eye
        Board board = boardRepository.findById(6);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    } // rollback (@Transactional)

    // 테스트 코드에서는 의존성 주입해놔도 테스트 메소드 파라미터에 매개변수로는 안넣음
    // => 테스트는 JVM이 실행하기 때문에 제어가 안됨 (테스트 코드 문법으로 알아둘것)
    @Test // 테스트할 메소드 (테스트할 자체 스레드 생성됨)
    public void findAll_test() { // 테스트 메소드 이름끝에 _test 붙이는게 약속
        
        // 테스트 3단계 : given - when - then(초보자들은 eye)
        
        // given : 테스트할 메소드의 파라미터
        // 이 경우, 테스트할 BoardRepository의 findAll() 메소드의 파라미터에는 매개변수 없음
        // => given 생략 가능

        // when : 테스트할 메소드 (데이터를 어디서 찾을건지)
        List<Board> boardList = boardRepository.findAll();
        System.out.println();

        // tnen : 상태 검증하는 곳
        // eye(눈으로 보다) : 초보자들은 눈으로 봐야하므로 then보다는 eye를 해보는것이 적합
        for(Board board : boardList) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("=======================");
        }
    }
}