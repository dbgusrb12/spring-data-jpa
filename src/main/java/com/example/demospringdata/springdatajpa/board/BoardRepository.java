package com.example.demospringdata.springdatajpa.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleStartsWith(String title);

    // NamedQuery 어노테이션을 사용해 JPQL을 작성 할 수 있다.
    List<Board> findByTitleWithNamedQuery(String title);

    // NamedNativeQuery 어노테이션을 사용해 native query 도 작성 할 수 있다.
    List<Board> findByTitleWithNamedNativeQuery(String title);

    // Query 어노테이션을 이용해 namedQuery 대신 사용 할 수 있다.
    @Query("SELECT b FROM Board AS b WHERE b.title = ?1")
    List<Board> findByTitleWithQueryAnnotation(String title);

    // Query 어노테이션을 이용해 native query 역시 작성 가능하다.
    @Query(value = "SELECT * FROM BOARD WHERE title = ?1", nativeQuery = true)
    List<Board> findByTitleWithNativeQuery(String title);

}
