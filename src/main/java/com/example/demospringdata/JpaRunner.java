package com.example.demospringdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaRunner implements ApplicationRunner {

    @Autowired
    HibernateSample hibernateSample;

    @Autowired
    JpaSample jpaSample;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        // JPA 사용하여 account 등록
        jpaSample.createAccountWithJpa();

        // Hibernate 사용하여 account 등록
        hibernateSample.createAccountWithHibernate();

        // Account, Study 양방향 매핑 등록
        hibernateSample.createAccountAndStudy("hyungyu3");

        // Entity Status 확인 (Transient, Persistent, Detached, Removed)
        Account account = hibernateSample.createAccountAndStudy("hyungyu4");
        hibernateSample.detachedToPersistent(account);

        // CascadeType.PERSIST 확인
        Post post = hibernateSample.cascadePersistentTest();

        // CascadeType.REMOVE 확인
        hibernateSample.cascadeRemoveTest(post);

        // FetchType.EAGER 전략 확인
        Post fetchEagerPost = hibernateSample.cascadePersistentTest();
        hibernateSample.fetchEagerTest(fetchEagerPost);

        // FetchType.LAZY 전략 확인
        Post fetchLazyPost = hibernateSample.cascadePersistentTest();
        hibernateSample.fetchLazyTest(fetchLazyPost);

        // JPA 사용하여 쿼리 생성 및 실행 확인
        hibernateSample.cascadePersistentTest();
        jpaSample.getPostsByCreateQuery();

        // JPA CriteriaBuilder 를 사용하여 Type safety 쿼리 생성 및 실행 확인
        hibernateSample.cascadePersistentTest();
        jpaSample.getPostsByCreateQueryCriteria();

        // JPA namedQuery 를 사용하여 쿼리 생성 및 실행 확인
        hibernateSample.cascadePersistentTest();
        jpaSample.getPostsByNamedQuery();

        // JPA nativeQuery 를 사용하여 쿼리 생성 및 실행 확인
        hibernateSample.cascadePersistentTest();
        jpaSample.getPostsByNativeQuery();
    }
}
