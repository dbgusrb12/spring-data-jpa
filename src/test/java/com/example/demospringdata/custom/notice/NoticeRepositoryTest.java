package com.example.demospringdata.custom.notice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        // Insert 쿼리가 Select 쿼리에 영향을 미치기 때문에 Insert 쿼리 발생
        noticeRepository.findMyNotice();

        noticeRepository.delete(notice);

        // removed 상태인 notice 를 DB에 직접 반영한다.
        noticeRepository.flush();
    }
}