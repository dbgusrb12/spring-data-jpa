package com.example.demospringdata.repository;

import com.example.demospringdata.entity.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 초기 JPA 를 사용하여 repository 를 구현 했을 때는
 * 일반적인 기능들을 전부 만들어서 구현을 했다.
 */
@Repository
@Transactional
public class BasePostRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Post add(Post post) {
        entityManager.persist(post);
        return post;
    }

    public void delete(Post post) {
        entityManager.remove(post);
    }

    public List<Post> findAll() {
        return entityManager.createQuery("SELECT p FROM Post AS p", Post.class)
                .getResultList();
    }
}
