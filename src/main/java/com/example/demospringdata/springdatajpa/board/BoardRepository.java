package com.example.demospringdata.springdatajpa.board;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleStartsWith(String title);

    // NamedQuery 어노테이션을 사용해 JPQL 을 작성 할 수 있다.
    List<Board> findByTitleWithNamedQuery(String title);

    // NamedNativeQuery 어노테이션을 사용해 native query 도 작성 할 수 있다.
    List<Board> findByTitleWithNamedNativeQuery(String title);

    // Query 어노테이션을 이용해 namedQuery 대신 사용 할 수 있다.
    @Query("SELECT b FROM Board AS b WHERE b.title = ?1")
    List<Board> findByTitleWithQueryAnnotation(String title);

    // Query 어노테이션을 이용해 native query 역시 작성 가능하다.
    @Query(value = "SELECT * FROM BOARD WHERE title = ?1", nativeQuery = true)
    List<Board> findByTitleWithNativeQuery(String title);

    @Query("SELECT b FROM Board AS b WHERE b.title = ?1")
    List<Board> findByTitleOrderByTitle(String title, Sort sort);

    // JPQL 을 named parameter 를 사용해 작성 할 수 있다.
    // 기본적으로는 변수명을 따라가지만, 변수명이 다르다면 Param 어노테이션을 사용해 정의할 수 있다.
    // SpEL (Spring Expression Language) 를 사용하여 엔티티의 이름을 직접 작성 할 필요 없이 가져다 쓸 수 있다.
    @Query("SELECT b FROM #{#entityName} AS b WHERE b.title = :title")
    List<Board> findByTitleWithNamedParameter(@Param("title") String keyword);

    // Modifying 어노테이션을 사용해 update 쿼리라는걸 명시해준다.
    @Modifying
    @Query("UPDATE Board b SET b.title = ?1 WHERE b.id = ?2")
    int updateTitle(String title, Long id);

    // clearAutomatically 의 값을 true 로 설정하면 해당 쿼리를 실행 하고 난 뒤, 캐시를 비워준다.
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Board b SET b.title = ?1 WHERE b.id = ?2")
    int updateBoardTitle(String title, Long id);

}
