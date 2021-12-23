package com.example.demospringdata.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * custom 하게 작성한 메서드들을 공통으로 모아서 사용 할 수 있다.
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends Repository<T, ID> {

    <E extends T> E save(E comment);

    List<T> findAll();

}
