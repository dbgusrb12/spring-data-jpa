package com.example.demospringdata.repository;

import com.example.demospringdata.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        // Given
        Comment comment = new Comment();
        comment.setComment("Hello Comment");
        commentRepository.save(comment);

        // When
        List<Comment> all = commentRepository.findAll();
        // Then
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void queryMethodTest() {
        // Given
        Comment newComment = new Comment();
        newComment.setLikeCount(100);
        newComment.setComment("spring data jpa");
        commentRepository.save(newComment);

        // When
        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("Spring", 10);
        // Then
        assertThat(comments.size()).isEqualTo(1);
    }

    @Test
    public void orderByDescTest() {
        // Given
        this.createComment(100, "jpa - spring boot");
        this.createComment(50, "hibernate - SPRING BOOT");

        // When
        List<Comment> orderByComments = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("Spring");

        // Then
        assertThat(orderByComments.size()).isEqualTo(2);
        assertThat(orderByComments).first().hasFieldOrPropertyWithValue("likeCount", 100);
    }

    @Test
    public void orderByAscTest() {
        // Given
        this.createComment(100, "jpa - spring boot");
        this.createComment(50, "hibernate - SPRING BOOT");

        // When
        List<Comment> orderByComments = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountAsc("Spring");

        // Then
        assertThat(orderByComments.size()).isEqualTo(2);
        assertThat(orderByComments).first().hasFieldOrPropertyWithValue("likeCount", 50);
    }

    @Test
    public void pageTest() {
        this.createComment(100, "spring data jpa");
        this.createComment(50, "SPRING - jpa");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount"));

        Page<Comment> pageComments = commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);
        assertThat(pageComments.getNumberOfElements()).isEqualTo(2);
        assertThat(pageComments).first().hasFieldOrPropertyWithValue("likeCount", 100);
    }

    @Test
    public void streamTest() {
        this.createComment(100, "spring data jpa");
        this.createComment(50, "SPRING - jpa");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount"));

        // Stream Api 를 사용 할 때는 try-with-resource 문법을 사용할 것. (Stream 을 다 사용 한 뒤 close() 를 해야하기 때문에)
        try (Stream<Comment> streamComments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("Spring", 10, pageRequest)) {
            Comment comment = streamComments.findFirst().get();
            assertThat(comment.getLikeCount()).isEqualTo(100);
        }
    }

    @Test
    public void asyncTest() throws ExecutionException, InterruptedException {
        this.createComment(100, "spring data jpa");
        this.createComment(50, "Spring - hibernate");
        ListenableFuture<List<Comment>> future = commentRepository.findByCommentContainsIgnoreCase("Spring");
        System.out.println("===========");
        System.out.println("is done? " + future.isDone());

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex);
            }

            @Override
            public void onSuccess(List<Comment> result) {
                // Sout 이 안찍히는 이유? => Main Thread 가 종료되었기 때문에
                System.out.println("======= Async =======");
                System.out.println(result.size());
                result.forEach(System.out::println);
            }
        });
    }

    private void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setLikeCount(likeCount);
        newComment.setComment(comment);
        commentRepository.save(newComment);
    }
}