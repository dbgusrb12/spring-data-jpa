package com.example.demospringdata.repository;

import com.example.demospringdata.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring data JPA 에서 구현한 JpaRepository 를 상속받아, 기본적인 기능을 미리 구현해준다.
 * Spring boot 에서 main 클래스에 @EnableJpaRepositories 어노테이션을 넣어줌
 */
public interface PostRepository extends JpaRepository<Post, Long> {

}
