package com.example.demospringdata.custom.notice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
// Bean 설정 파일을 import 해서 해당 Bean 이 Test 코드에서도 주입 될 수 있게 한다.
@Import(NoticeRepositoryTestConfig.class)
public class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    ApplicationContext applicationContext;

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

    @Test
    public void event() {
        Notice notice = new Notice();
        notice.setTitle("event");
        noticeRepository.save(notice);

        NoticePublishedEvent event = new NoticePublishedEvent(notice);
        applicationContext.publishEvent(event);
    }

    @Test
    public void springDataEventTest() {
        Notice notice = new Notice();
        notice.setTitle("hibernate");

        noticeRepository.save(notice.publish());
    }
}