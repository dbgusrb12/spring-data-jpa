package com.example.demospringdata.repository;

import com.example.demospringdata.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void crud() {
        // Given
        Account account = new Account();
        account.setUsername("hyungyu");
        account.setPassword("pass");

        // When
        accountRepository.save(account);

        // Then
        List<Account> all = accountRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void optionalTest() {
        Optional<Account> byId = accountRepository.findById(100l);
        assertThat(byId).isEmpty();
//        Account account = byId.orElseThrow(IllegalArgumentException::new);
    }

    @Test
    public void findContainsTest() {
        // Given
        Account account = new Account();
        account.setUsername("hyungyu");
        account.setPassword("pass");
        accountRepository.save(account);

        // When
        List<Account> hyun = accountRepository.findByUsernameContains("hyun");

        // Then
        assertThat(hyun.size()).isEqualTo(1);
    }

    @Test
    public void pageTest() {
        // Given
        Account account = new Account();
        account.setUsername("hyungyu");
        account.setPassword("pass");
        accountRepository.save(account);

        // When
        Page<Account> pageAccount = accountRepository.findByLikeCountGreaterThanOrderByIdDesc(-1, PageRequest.of(0, 5));

        // Then
        assertThat(pageAccount.getTotalElements()).isEqualTo(1);
    }

}