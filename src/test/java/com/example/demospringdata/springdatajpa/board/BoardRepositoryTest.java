package com.example.demospringdata.springdatajpa.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save() {
        // save 메서드는 해당 객체가 존재하면 merge 메서드, 존재하지 않으면 persist 메서드를 호출한다.
        // persist 메서드는 파라미터로 전달 된 엔티티를 persist 상태로 만든 뒤 return
        // merge 메서드는 파라미터로 전달 된 엔티티의 복사본을 만들어, 해당 객체를 persist 상태로 만든 뒤 return
        // 따라서, save 메서드를 호출 한 뒤 다시 수정 할 일이 생기면, return 받은 객체를 사용해 수정하는 것을 권장한다.

        Board board = new Board();
        board.setTitle("spring-data-jpa");

        // board 의 id 값이 없으므로 persist() 호출 (insert 쿼리 발생)
        Board saveBoard = boardRepository.save(board);

        // persist 메서드를 사용하기 때문에, 파라미터로 전달된 board 와 return 된 saveBoard 는 같은 객체이다.
        assertThat(entityManager.contains(board)).isTrue();
        assertThat(entityManager.contains(saveBoard)).isTrue();
        assertThat(board == saveBoard).isTrue();

        Board boardUpdate = new Board();
        boardUpdate.setId(saveBoard.getId());
        boardUpdate.setTitle("hibernate");

        // boardUpdate 의 id 값이 있으므로 merge() 호출 (해당 엔티티가 존재하면 update, 존재하지 않으면 insert 쿼리 발생)
        Board updatedBoard = boardRepository.save(boardUpdate);

        // merge 메서드를 사용하기 때문에, 파라미터로 전달된 boardUpdate 와 return 된 updatedBoard 는 다른 객체이다.
        assertThat(entityManager.contains(boardUpdate)).isFalse();
        assertThat(entityManager.contains(updatedBoard)).isTrue();
        assertThat(boardUpdate == updatedBoard).isFalse();


        // findAll 메서드를 호출하는 이유? => 해당 메서드를 호출하지 않으면 insert 쿼리가 발생하지 않는다. 지금 시점에서 이 이유를 모른다면 다시 공부 해야 됨.
        List<Board> all = boardRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartsWith() {
        this.saveBoard();

        List<Board> spring = boardRepository.findByTitleStartsWith("Spring");
        assertThat(spring.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleWithNamedQuery() {
        this.saveBoard();

        List<Board> spring = boardRepository.findByTitleWithNamedQuery("Spring Data Jpa");
        assertThat(spring.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleWithNamedNativeQuery() {
        this.saveBoard();

        List<Board> spring = boardRepository.findByTitleWithNamedNativeQuery("Spring Data Jpa");
        assertThat(spring.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleWithQueryAnnotation() {
        this.saveBoard();

        List<Board> spring = boardRepository.findByTitleWithQueryAnnotation("Spring Data Jpa");
        assertThat(spring.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleWithNativeQuery() {
        this.saveBoard();

        List<Board> spring = boardRepository.findByTitleWithNativeQuery("Spring Data Jpa");
        assertThat(spring.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleOrderByTitle() {
        this.saveBoard();

        List<Board> spring = boardRepository.findByTitleOrderByTitle("Spring Data Jpa", Sort.by("title"));
        assertThat(spring.size()).isEqualTo(1);
        // JpaSort 라는 클래스를 사용하여, DB 에서 사용하는 함수를 호출 할 수 있다.
        List<Board> notPropertiesInSpring = boardRepository.findByTitleOrderByTitle("Spring Data Jpa", JpaSort.unsafe("LENGTH(title)"));
        assertThat(notPropertiesInSpring.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleWithNamedParameter() {
        this.saveBoard();

        List<Board> spring = boardRepository.findByTitleWithNamedParameter("Spring Data Jpa");
        assertThat(spring.size()).isEqualTo(1);
    }

    @Test
    public void updateTitle() {
        Board spring = this.saveBoard();

        String hibernate = "hibernate";
        int update = boardRepository.updateTitle(hibernate, spring.getId());
        assertThat(update).isEqualTo(1);

        // 해당 테스트가 실패하는 이유?
        // Transaction 이 끝나지 않았기 때문에, 해당 객체는 아직 Persistent Context 에 캐싱이 되어있고,
        // hibernate 1차 캐싱으로 인해 캐시에 남아있는 데이터를 가져온다.
        // 때문에 Select 쿼리가 발생하지 않고, title 이 여전히 Spring Data Jpa 이다.
        Optional<Board> byId = boardRepository.findById(spring.getId());
        assertThat(byId.get().getTitle()).isEqualTo(hibernate);
    }

    @Test
    public void updateBoardTitle() {
        Board spring = this.saveBoard();

        String hibernate = "hibernate";
        // Modifying 어노테이션의 clearAutomatically 를 true 로 설정해서, 해당 쿼리가 발생 한 후 캐시를 지워준다.
        int update = boardRepository.updateBoardTitle(hibernate, spring.getId());
        assertThat(update).isEqualTo(1);

        Optional<Board> byId = boardRepository.findById(spring.getId());
        assertThat(byId.get().getTitle()).isEqualTo(hibernate);
    }

    @Test
    public void updateTitleWithoutRepository() {
        // UPDATE 쿼리를 직접 만들어서 사용하는건 권장하지 않는다. 왜?
        // 명시적으로 UPDATE 쿼리를 실행 하지 않아도,
        // 로직을 통해 쉽고 간단하게 데이터의 변경을 DB에 반영이 되도록 hibernate 가 제공해주는데
        // 위의 두 테스트 예제처럼 flush 나 clear 를 사용해 복잡하게 UPDATE 를 실행 할 필요가 없다.

        Board spring = this.saveBoard();
        spring.setTitle("hibernate");

        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo("hibernate");
    }

    private Board saveBoard() {
        Board board = new Board();
        board.setTitle("Spring Data Jpa");
        return boardRepository.save(board);
    }
}