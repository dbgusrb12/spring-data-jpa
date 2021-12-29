package com.example.demospringdata.custom.notice;

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
public class DefaultNoticeRepositoryTest {
    @Autowired
    DefaultNoticeRepository defaultNoticeRepository;

    @Test
    public void crud() {
        Notice notice = new Notice();
        notice.setTitle("hibernate");
        assertThat(defaultNoticeRepository.contains(notice)).isFalse();
        defaultNoticeRepository.save(notice);
        assertThat(defaultNoticeRepository.contains(notice)).isTrue();
    }

    @Test
    public void querydslTest() {
        Notice notice = new Notice();
        notice.setTitle("hibernate");
        defaultNoticeRepository.save(notice);

        Predicate predicate = QNotice.notice.title.containsIgnoreCase("hi");
        Optional<Notice> one = defaultNoticeRepository.findOne(predicate);
        assertThat(one).isNotEmpty();
    }
}