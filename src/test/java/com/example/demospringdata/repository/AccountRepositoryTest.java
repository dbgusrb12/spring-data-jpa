package com.example.demospringdata.repository;

import com.example.demospringdata.entity.Account;
import com.example.demospringdata.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    @Rollback(false)
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
}