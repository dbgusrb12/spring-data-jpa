package com.example.demospringdata.custom.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Custom Repository 의 구현체. 해당 구현체에서 원하는 로직을 구현한다.
 * CustomRepository 의 기본 postfix 는 Impl 이지만,
 * 변경 하고 싶으면 Main 클래스에 @EnableJpaRepositories 의 repositoryImplementationPostfix 설정 값을 바꿔준다.
 */
@Repository
@Transactional
public class NoticeCustomRepositoryDefault implements NoticeCustomRepository<Notice> {

    @Autowired
    EntityManager entityManager;

    /**
     * 원하는 메서드를 구현한다.
     *
     * @return
     */
    @Override
    public List<Notice> findMyNotice() {
        System.out.println("custom findMyPost");
        return entityManager.createQuery("SELECT n FROM Notice AS n", Notice.class)
                .getResultList();
    }

    @Override
    public void delete(Notice entity) {
        System.out.println("custom delete");
        entityManager.remove(entity);
    }
}
