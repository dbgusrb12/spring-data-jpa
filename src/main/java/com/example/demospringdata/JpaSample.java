package com.example.demospringdata;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class JpaSample {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * JPA EntityManager 를 사용하여 account 등록
     */
    public void createAccountWithJpa() {
        Account account = new Account();
        account.setUsername("hyungyu");
        account.setPassword("jpa");

        entityManager.persist(account);
    }

    /**
     * JPA EntityManager 를 사용하여 쿼리 생성 (JPQL)
     */
    public void getPostsByCreateQuery() {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
        List<Post> posts = query.getResultList();
        posts.forEach(System.out::println);
    }

    /**
     * JPA CriteriaBuilder 를 사용하여 쿼리 생성 (Type Safety 쿼리 생성)
     */
    public void getPostsByCreateQueryCriteria() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> query = builder.createQuery(Post.class);
        Root<Post> root = query.from(Post.class);
        query.select(root);

        List<Post> posts = entityManager.createQuery(query).getResultList();
        posts.forEach(System.out::println);
    }

    /**
     * JPA NamedQuery 를 사용하여 쿼리 생성 (mybatis 처럼 미리 정의 된 쿼리 사용 가능)
     */
    public void getPostsByNamedQuery() {
        TypedQuery<Post> query = entityManager.createNamedQuery("all_post", Post.class);
        List<Post> posts = query.getResultList();
        posts.forEach(System.out::println);
    }

    /**
     * JPA nativeQuery 를 사용하여 쿼리 생성 (실제 쿼리문과 동일한 문법의 쿼리 작성)
     */
    public void getPostsByNativeQuery() {
        List<Post> posts = entityManager.createNativeQuery("SELECT * FROM POST", Post.class).getResultList();
        posts.forEach(System.out::println);
    }
}
