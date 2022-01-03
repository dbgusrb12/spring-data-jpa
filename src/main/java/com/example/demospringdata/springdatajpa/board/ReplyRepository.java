package com.example.demospringdata.springdatajpa.board;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 선언 되어 있는 EntityGraph 를 사용 할 때
    // EntityGraph 의 type 은 LOAD, FETCH 가 있는데,
    // LOAD 는 설정한 attribute 만 EAGER, 나머지는 기본 패치 전략 사용
    // FETCH (기본값) 는 설정한 attribute 만 EAGER 이고, 나머지는 전부 LAZY 로 설정된다.
    @EntityGraph(value = "Reply.board", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Reply> getReplyById(Long id);

    // 직접 EntityGraph 를 선언 할 때
    @EntityGraph(attributePaths = "board")
    Optional<Reply> getReplyByIdOrderByIdAsc(Long id);

    // Overriding 이 안될 때, 해당 타입을 매개변수로 받아서 사용 할 수 있다. (다이나믹 프로젝션)
    <T> List<T> findByBoard_Id(Long id, Class<T> clazz);

}
