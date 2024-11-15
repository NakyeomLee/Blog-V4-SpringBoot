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

    public List<Board> findAll() {
        // Board.class => Board 클래스에 mapping
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        // q.getResultList() : 컬렉션(List)로 알아서 받아줌
        return q.getResultList();
    }
}