package com.example.demospringdata.repository;

import com.example.demospringdata.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.stream.Stream;

/**
 * JpaRepository 를 구현하지 않고, Repository 인터페이스를 직접 정의 할 수 있다.
 */
@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository {

    Comment save(Comment comment);

    List<Comment> findAll();

    /**
     * ignoreCase 를 사용해 대소문자 구문 없이 가능하다.
     * greaterThan 을 사용해 해당 숫자 이상으로 검색 가능하다.
     *
     * @param keyword
     * @return
     */
    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, Integer likeCount);

    /**
     * orderBy Desc 를 사용해 해당 컬럼을 역순으로 정렬 할 수 있다.
     *
     * @param keyword
     * @return
     */
    List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);

    /**
     * orderBy Asc 를 사용해 해당 컬럼을 정렬 할 수 있다.
     *
     * @param keyword
     * @return
     */
    List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountAsc(String keyword);

    /**
     * pageable 을 이용해 페이징 처리를 할 수 있다.
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

    /**
     * paging 처리 된 리스트를 Stream을 사용해 받을 수 있다.
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Stream<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, Integer likeCount, Pageable pageable);

}
