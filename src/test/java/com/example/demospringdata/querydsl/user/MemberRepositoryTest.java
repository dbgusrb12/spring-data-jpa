package com.example.demospringdata.querydsl.user;

import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void crud() {
        QMember member = QMember.member;
        Predicate predicate = member
                .firstName.containsIgnoreCase("hyungyu")
                .and(member.lastName.startsWith("yu"));
        Optional<Member> one = memberRepository.findOne(predicate);
        assertThat(one).isEmpty();
    }
}