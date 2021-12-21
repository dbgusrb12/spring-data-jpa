package com.example.demospringdata;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("hyungyu");
        account.setPassword("jpa");

        Study study = new Study();
        study.setName("Spring Data JPA");

        // 객체 지향 적인 코드를 위해 두 객체를 서로 매핑 해주는 메서드를 만든다.
        account.addStudy(study);

        // jpa 사용
//        entityManager.persist(account);

        // hibernate api 사용
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);
    }
}
