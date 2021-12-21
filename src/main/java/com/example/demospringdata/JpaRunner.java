package com.example.demospringdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaRunner implements ApplicationRunner {

    @Autowired
    JpaSample jpaSample;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        // JPA 사용하여 account 등록
        jpaSample.createAccountWithJpa();

        // Hibernate 사용하여 account 등록
        jpaSample.createAccountWithHibernate();

        // Account, Study 양방향 매핑 등록
        jpaSample.createAccountAndStudy("hyungyu3");

        // Entity Status 확인 (Transient, Persistent, Detached, Removed)
        Account account = jpaSample.createAccountAndStudy("hyungyu4");
        jpaSample.detachedToPersistent(account);


        // CascadeType.PERSIST 확인
        Post post = jpaSample.cascadePersistentTest();

//         CascadeType.REMOVE 확인
        jpaSample.cascadeRemoveTest(post);

        Post fetchPost = jpaSample.cascadePersistentTest();
        // FetchType.EAGER 전략 확인
        jpaSample.fetchEagerTest(fetchPost);

        // FetchType.LAZY 전략 확인
        jpaSample.fetchLazyTest(fetchPost);

    }
}
