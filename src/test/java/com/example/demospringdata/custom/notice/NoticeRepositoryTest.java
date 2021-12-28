package com.example.demospringdata.custom.notice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Test
    public void crud() {
        Notice notice = new Notice();
        notice.setTitle("hibernate");
        noticeRepository.save(notice);

        noticeRepository.findMyNotice();

        noticeRepository.delete(notice);
        noticeRepository.flush();
    }
}