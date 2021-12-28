package com.example.demospringdata.custom;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 기본 repository 를 커스터 마이징 하기 위해 필요한 Interface JpaRepository 를 상속받고, 필요한 메서드를 선언한다.
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    boolean contains(T entity);
}
