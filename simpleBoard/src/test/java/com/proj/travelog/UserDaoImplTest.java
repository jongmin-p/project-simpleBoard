package com.proj.simpleBoard;

import com.proj.simpleBoard.domain.User;
import com.proj.simpleBoard.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    UserDao userdao;

    @Test
    public void deleteUser() {
    }

    @Test
    public void selectUser() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void updateUser() throws Exception {
        // 추가 (날짜 및 시간 때문에 에러가 떠서, 뒤에 시간을 자르기 위해 넣은 코드)
        Calendar cal = Calendar.getInstance();
        cal.clear();            // clear() 해줘야 모든 필드가 초기화 되고, 날짜만 들어감
        cal.set(2021, 1, 1);


        userdao.deleteAll();

        User user = new User("asdf", "1234", "Smith", "010-1234-5678", "aaa@aaa.com", new Date());

        // 1. 위 객체를 일단 INSERT 해보자
        int rowCnt = userdao.insertUser(user);

        // 2. 잘 들어갔는지 확인
        assertTrue(rowCnt == 1);

        // 3. 이제 내용을 바꿔보자
        user.setPwd("4321");
        user.setEmail("bbb@bbb.com");

        // 4. 그리고 UPDATE
        rowCnt = userdao.updateUser(user);

        // 5. 다시 테스트
        assertTrue(rowCnt == 1);

        // 6. 우리가 변경한 내용을 보려면 SELECT
        User user222 = userdao.selectUser(user.getId());

        // user 랑 user222 한 번 출력해보자
        System.out.println("user = " + user);
        System.out.println("user222 = " + user222);

        // 7. SELECT 로 가져온 것과 INSERT 한 것이 같아야겠지?
        assertTrue(user.equals(user222));

    }
}