package com.example.demospringdata.repository;

import com.example.demospringdata.entity.Comment;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * JpaRepository 를 구현하지 않고, Repository 인터페이스를 직접 정의 할 수 있다.
 */
@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository {

    Comment save(Comment comment);

    List<Comment> findAll();

}
