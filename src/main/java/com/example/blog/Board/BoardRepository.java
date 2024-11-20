package com.example.blog.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor // final이 붙어있는 필드에 대한 생성자를 만들어줌
@Repository
public class BoardRepository {

    // final 붙이면 new가 안됨
    // => new가 안되는데 다른곳에서 어떻게 쓰는가?
    // => 생성자가 있으면 됨 (@RequiredArgsConstructor가 만듦)

    // JPA는 EntityManager로 DB에 접근(Java에서의 DBConnection)
    private final EntityManager em;

    public void update(int id, String title, String content) {
        Query q = em.createNativeQuery("update board_tb set title=?, content=? where id=?");
        q.setParameter(1, title);
        q.setParameter(2, content);
        q.setParameter(3, id);
        q.executeUpdate();
    }

    // 1118 글삭제
    public void delete(int id) {
        Query q = em.createNativeQuery("delete from board_tb where id=?");
        q.setParameter(1, id);
        q.executeUpdate(); // insert, update, delete 때 사용 함
    }

    // 1118 글쓰기
    public void save(Board board) {
        Query q = em.createNativeQuery("insert into board_tb(title, content, created_at) values (?, ?, now())");
        q.executeUpdate(); // 넣고 커밋해 -정상 return값은 1
    }

    // 1118 글상세보기
    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        q.setParameter(1, id); // 물음표 완성하기 (물음표 순서, 물음표에 바인딩될 변수값)
        // (Board) : 다운캐스팅. getSingleResult 타입은 object
        return (Board) q.getSingleResult(); // 통신코드. 한개만 반환
    }

    public List<Board> findAll() {
        // Board.class => Board 클래스에 mapping
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        // q.getResultList() : 결과를 컬렉션(List)로 알아서 받아줌
        return q.getResultList();
    }
}