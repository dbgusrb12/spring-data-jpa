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

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        // Transient 상태 (객체를 생성 하고, JPA 가 모르는 상태)
//        Account account = new Account();
//        account.setUsername("hyungyu");
//        account.setPassword("jpa");
//
//        Study study = new Study();
//        study.setName("Spring Data JPA");
//
//        // 객체 지향 적인 코드를 위해 두 객체를 서로 매핑 해주는 메서드를 만든다.
//        account.addStudy(study);
//
//        // jpa 사용
////        entityManager.persist(account);
//
//        // hibernate api 사용
//        Session session = entityManager.unwrap(Session.class);
//
//        // Persistent 상태 (JPA 가 객체를 인식하고 관리중인 상태)
//        session.save(account);
//        session.save(study);
//
//        Account load = session.load(Account.class, account.getId());
//        load.setUsername("springboot");
//        load.setUsername("springboot2");
//        load.setUsername("springboot3");
//        load.setUsername("hyungyu");
//        System.out.println("======================");
//        System.out.println(load.getUsername());
//    }

    /**
     * cascade 옵션 확인 용 메서드
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // CascadeType.PERSIST
//        Post post = new Post();
//        post.setTitle("Spring Data JPA 강의중...");
//
//        Comment comment = new Comment();
//        comment.setComment("강의 듣는중.");
//        post.addComment(comment);
//
//        Comment comment1 = new Comment();
//        comment1.setComment("Spring Data JPA는 아직 못봄");
//        post.addComment(comment1);
//        Session session = entityManager.unwrap(Session.class);
//        session.save(post);

        // CascadeType.REMOVE
//        Session session = entityManager.unwrap(Session.class);
//        Post post = session.get(Post.class, 1l);
//
//        session.delete(post);



        Session session = entityManager.unwrap(Session.class);
        Post post = session.get(Post.class, 1l);
        System.out.println("==================");
        System.out.println(post.getTitle());
        System.out.println("==================");

        post.getComments().forEach(comment -> {
            System.out.println("===========");
            System.out.println(comment.getComment());
            System.out.println("===========");
        });

//        Session session = entityManager.unwrap(Session.class);
//        Comment comment = session.get(Comment.class, 2l);
//        System.out.println("==================");
//        System.out.println(comment.getPost().getTitle());
    }
}
