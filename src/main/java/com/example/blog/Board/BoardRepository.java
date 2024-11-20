package com.example.blog.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // final이 붙어있는 필드에 대한 생성자를 만들어줌
@Repository
public class BoardRepository {

    // final 붙이면 new가 안됨
    // => new가 안되는데 다른곳에서 어떻게 쓰는가?
    // => 생성자가 있으면 됨 (@RequiredArgsConstructor가 만듦)

    // JPA는 EntityManager로 DB에 접근(Java에서의 DBConnection)
    private final EntityManager em;

    // 글삭제
    public void delete(int id) {
        em.createQuery("delete from Board b where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    // 글쓰기
    public void save(Board board) {
        // 이 시점에서는 아직 비영속(영구 저장되지않고있음)
        em.persist(board);
        // 이 시점에서는 동기화 완료 => 영속화됨(영구 저장됨)
    }

    // 글상세보기
    public Optional<Board> findById(int id) {
        // 해당 아이디의 게시글이 있냐고 물어봄
        return Optional.ofNullable(em.find(Board.class, id));
    }

    public List<Board> findAll() {
        // createQuery는 createNativeQuery와 달리 객체 지향 쿼리(JPQL)
        // 테이블은 board_tb인데 여기서는 Board 클래스 객체를 들고옴
        return em.createQuery("select b from Board b order by b.id desc", Board.class)
                .getResultList();
    }
}