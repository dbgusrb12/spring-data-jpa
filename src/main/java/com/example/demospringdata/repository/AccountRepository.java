package com.example.demospringdata.repository;

import com.example.demospringdata.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountRepository extends MyRepository<Account, Long> {

    /**
     * Spring data jpa 가 쿼리를 만드는 전략 (QueryLookupStrategy)
     * - 메서드 이름을 이용하여 쿼리를 생성한다. => CREATE
     * - 미리 정의해둔 쿼리를 찾아 사용한다. => USE_DECLARED_QUERY
     * - 미리 정의한 쿼리를 찾아보고 없으면 만든다. => CREATE_IF_NOT_FOUND (기본 전략)
     *
     * @param keyword
     * @return
     */
//    @Query("SELECT a FROM Account AS a")
    List<Account> findByUsernameContains(String keyword);

    /**
     * Spring data jpa 에서 제공하는 여러 키워드를 사용하여 쿼리를 작성 할 수 있다.
     * @param likeCount
     * @param pageable
     * @return
     */
    Page<Account> findByLikeCountGreaterThanOrderByIdDesc(int likeCount, Pageable pageable);
}
