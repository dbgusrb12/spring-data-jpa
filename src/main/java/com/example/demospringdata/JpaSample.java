package com.example.demospringdata;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
     * Hibernate Session 을 사용하여 account 등록
     */
    public void createAccountWithHibernate() {
        Account account = new Account();
        account.setUsername("hyungyu2");
        account.setPassword("jpa");

        Session session = entityManager.unwrap(Session.class);
        session.save(account);
    }

    /**
     * Account, Study 등록
     *
     * @param username: custom username
     * @return
     */
    public Account createAccountAndStudy(String username) {
        // Transient 상태 (객체를 생성 하고, JPA 가 모르는 상태)
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("jpa");

        Study study = new Study();
        study.setName("Spring Data JPA");
        // 객체 지향 적인 코드를 위해 두 객체를 서로 매핑 해주는 메서드를 만든다.
        account.addStudy(study);

        Session session = entityManager.unwrap(Session.class);
        // Persistent 상태 (JPA 가 객체를 인식하고 관리중인 상태)
        session.save(account);
        session.save(study);

        return account;
    }

    /**
     * detached 상태, persistent 상태 확인
     *
     * @param account: detached Object
     */
    public void detachedToPersistent(Account account) {
        // Detached 상태 일 경우 객체를 변경해도 DB에 반영 되지 않는다.
        account.setUsername("springboot");

        Session session = entityManager.unwrap(Session.class);

        // Persistent 상태 일 경우 객체의 값이 바뀌면 DB에 반영이 된다.
        // 하지만 1차 캐시, dirty checking, write behind 전략을 사용하여 아무리 많이 변경되어도 마지막 바뀐 값이 처음 캐시된 값과 같은 값으로 변경되면 update 쿼리는 날아가지 않는다.
        Account load = session.load(Account.class, account.getId());
        load.setUsername("springboot");
        load.setUsername("springboot1");
        load.setUsername("springboot2");
        load.setUsername("springboot3");
        load.setUsername("hyungyu4");
    }

    /**
     * Cascade 전략 테스트(PERSIST)
     *
     * @return
     */
    public Post cascadePersistentTest() {
        Post post = new Post();
        post.setTitle("Spring Data JPA 강의중...");

        Comment comment = new Comment();
        comment.setComment("강의 듣는중.");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("Spring Data JPA는 아직 못봄");
        post.addComment(comment1);
        Session session = entityManager.unwrap(Session.class);
        // cascade persist 전략으로 post 엔티티만 저장 되어도 하위 comment 리스트가 저장된다.
        session.save(post);
        return post;
    }

    /**
     * Cascade 전략 테스트(REMOVE)
     *
     * @param post: remove Object
     */
    public void cascadeRemoveTest(Post post) {
        Session session = entityManager.unwrap(Session.class);
        Post load = session.get(Post.class, post.getId());

        // cascade remove 전략으로 post 엔티티만 삭제 되어도 하위 comment 리스트가 삭제된다.
        session.delete(load);
    }

    /**
     * Fetch 전략 테스트 (EAGER)
     *
     * @param post
     */
    public void fetchEagerTest(Post post) {
        Session session = entityManager.unwrap(Session.class);
        Comment comment = session.get(Comment.class, post.getComments().stream().findFirst().get().getId());
        System.out.println("==================");
        // ManyToOne 의 기본 fetch 전략은 Eager (Comment 를 조회 할 때 Post 도 같이 조회한다.)
        System.out.println(comment.getPost().getTitle());
        System.out.println("==================");
    }

    /**
     * Fetch 전략 테스트 (LAZY)
     *
     * @param post
     */
    public void fetchLazyTest(Post post) {
        Session session = entityManager.unwrap(Session.class);
        Post load = session.get(Post.class, post.getId());
        System.out.println("==================");
        System.out.println(load.getTitle());
        System.out.println("==================");
        // OneToMany 의 기본 fetch 전략은 Lazy (Post 를 조회 할 때는 Post 만 조회를 하고, Comment 를 조회 할 일이 생기면 그 때 조회한다.)
        load.getComments().forEach(comment -> {
            System.out.println("===========");
            System.out.println(comment.getComment());
            System.out.println("===========");
        });
    }

}
