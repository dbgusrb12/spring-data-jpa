package com.example.demospringdata.custom;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * SimpleJpaRepository 를 상속 받고, 기본 Repository 를 대신하기 위해 만들어 놓은 MyRepository 를 구현한다.
 * 기본 Repository 를 커스터마이징 한 경우 Main 클래스에 @EnableJpaRepositories 의 repositoryBaseClass 를 명시해준다.
 *
 * @param <T>
 * @param <ID>
 */
public class SimpleMyRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    private EntityManager entityManager;

    public SimpleMyRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }

    /**
     * 기존 있는 메서드를 오버라이딩 해서 구현 할 수 있다.
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        return super.findAll();
    }
}
