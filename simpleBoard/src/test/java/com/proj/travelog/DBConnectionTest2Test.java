package com.proj.simpleBoard;

import com.proj.simpleBoard.domain.User;          // signup.web 폴더에 있는 User 아님. (이건 예전 회원가입용)
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {

    @Autowired
    DataSource ds;


    // insertUser() 메서드를 "테스트하는 메서드"
    @Test
    public void insertUserTest() throws Exception {

        // 4. 어떨 때는 이 test 가 돌아가고, 어떨 때는 id 중복 등 다양한 이유로 인해 안 돌아가면 안되잖아?
            // 그래서, 맨 앞에다가 deleteAll() 이라는 메서드를 생성해줘서 미리 테이블의 데이터들을 지우자.
            // 이게 있으면, 같은 데이터로 계속 테스트에 성공할 수 있다. (id 중복이 안되니까)
        deleteAll();


        // 1. 객체 생성
        User user = new User("asdf2", "1234", "Smith", "aaa@aaa.com", "fb", new Date());

        // 2. 객체 만든 다음에 inserUser() 호출
            // (insertUser 메서드의 반환결과는 int 니까 받아주고, 예외도 던져주니까 여기서도 예외 선언해주기)
        int rowCnt = insertUser(user);      // 성공하면 1이 반환될거고, 실패하면 0이 반환됨

        // 3. 결과 확인 (user_info 테이블에 데이터 1줄(rowCnt) 이 INSERT 되면 성공)
        assertTrue(rowCnt == 1);
    }


    // selectUser() 를 테스트하는 메서드
    @Test
    public void selectUserTest() throws Exception {
        // 3. 근데 만약에 테이블에 asdf2 라는 아이가 없으면, 올바른 로직이더라도 이 테스트는 실패하겠지?
        // 그래서 조회하기 전에 deleteAll() 을 먼저한 후, 다시 insertUser() 로 데이터를 넣어주고, 조회!
        deleteAll();

        User user = new User("asdf2", "1234", "abc", "aaa@aaa.com", "fb", new Date());
        int rowCnt = insertUser(user);      // 성공하면 1이 반환될거고, 실패하면 0이 반환됨



        // 1. id 가 asdf2 인 아이를 조회.     그 결과는 User 로 받는다.
        User user222 = selectUser("asdf2");

        // 2. 테스트 결과 확인    (id 가 asdf2 인 아이를 조회했으니까, 조회한 데이터의 id 도 같아야겠지?)
        assertTrue(user222.getId().equals("asdf2"));
    }


    // deleteUser 를 테스트하는 메서드
    @Test
    public void deleteUserTest() throws Exception {
        // 1. 첫번째 테스트
        // 테이블의 데이터를 다 지운 다움에, id 를 이용해서 삭제하려고 하면 0이 나와야 함.
        deleteAll();
        int rowCnt = deleteUser("asdf");            // 이미 다 지웠으니 0이 나와야 통과!
        assertTrue(rowCnt == 0);


        // 2. 두번째 테스트
        // 데이터 모두 지운 다움, 다시 데이터 한 줄 넣고, 그걸 delete 하려고 하면 1 이 나와야 함.
        User user = new User("asdf2", "1234", "abc", "aaa@aaa.com", "fb", new Date());
        rowCnt = insertUser(user);                  // 여기서는 rowCnt 가 1 이어야 함.
        assertTrue(rowCnt == 1);

        rowCnt = deleteUser(user.getId());          // 그리고 deleteUser 하면 여기서도 1 이어야 함.
        assertTrue(rowCnt == 1);


        // SELECT 까지 해보자.       (위에서 다시 지웠으니까 결과가 null 이어야 정상 - 통과)
        assertTrue(selectUser(user.getId()) == null);


        // ↑↑↑ 위의 4가지 개별 테스트들을 모두 통과해야, 이 하나의 전체 테스트가 통과하는 듯!
    }


    // 숙제!!!    아래에서 updateUser() 메서드 먼저 만들어보고, 이 테스트 메서드 만들어보자!!
//    @Test
//    public void updateUserTest() throws Exception {
//        assertTrue();
//    }


    /////////////////////////////////////////////////////////////////////////////
    //////////////     ↓↓↓ 메서드들    ↑↑↑  아래의 메서드들을 테스트   ////////////////
    /////////////////////////////////////////////////////////////////////////////


    // 1. 사용자 정보를 user_info 테이블에 저장하기 위한 메서드
        // (여기서 쓰는 User 클래스는 signup.web 폴더에 있는 User 클래스가 아니다)
    public int insertUser(User user) throws Exception {
        // 일단, 데이터베이스 먼저 연결
        Connection conn = ds.getConnection();

        /*
            INSERT INTO user_info (id, pwd, name, email, birth, sns, reg_date)
            VALUES ('asdf22', '1234', 'smith', 'aaa@aaa.com', '2021-01-01', 'facebook', NOW());
        */

        String sql = "INSERT INTO user_info VALUES (?, ?, ?, ?, ?, ?, NOW())";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, user.getId());   // 첫번째 물음표의 값을 user 객체의 id 로 채움.
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getPhone());
        pstmt.setString(5, user.getEmail());

        // 몇 개의 행이 영향을 받았는지 int 로 받자.
            // .executeUpdate() 는 INSERT, UPDATE, DELETE 에만 사용 가능
            // SELECT 문에는 .executeQuery() 사용
        int rowCnt = pstmt.executeUpdate();

        return rowCnt;
    }


    // 2. 테이블에 존재하는 "모든 데이터" 들을 삭제      (개별 삭제는 아래에 따로 있다)
    private void deleteAll() throws Exception {
        // 일단, 데이터베이스 먼저 연결
        Connection conn = ds.getConnection();

        String sql = "DELETE FROM user_info";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        int rowCnt = pstmt.executeUpdate();
    }


    // 3. user_info 테이블에서 사용자 정보를 가져오는 메서드
        // user_info 테이블의 key 가 id 니까 파라미터는 String id 만 있으면 됨.
        // id 를 주면, 이 id 에 해당하는 사람의 정보를 가져옴.
    public User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "SELECT * FROM user_info WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        // SELECT 문은 executeQuery() 이고,     결과는 ResultSet 이다.
        ResultSet rs = pstmt.executeQuery();

        // 지금은 id 를 가지고 조회하니까 rs 가 1이 나올거임 (데이터 1줄).
        // 만약에 조회된 결과가 있으면, 객체 생성하고, 데이터 채워넣어라
        if(rs.next()) {
            User user = new User();

            // 이거 좀 헷갈린다. 복습 (객체에 데이터 채우기)
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setPhone(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setReg_date(new Date(rs.getTimestamp(6).getTime()));

            // 이제 데이터를 채운 객체를 반환
            return user;
        }

        // 조회된 결과가 없으면 그냥 null 반환
        return null;
    }


    // 4. 특정 id 를 이용해서,     테이블에 존재하는 개별 데이터를 삭제하는 메서드
    public int deleteUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "DELETE FROM user_info WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        // int rowCnt = pstmt.executeUpdate();
        // return rowCnt;

        return pstmt.executeUpdate();

    }


    // 숙제!!!    -   매개변수로 받은 사용자 정보로 user_info테이블을 update하는 메서드
    // 테스트까지 만들어보자!!
    public int updateUser(User user) throws Exception {
        return 0;
    }


    ////////////////////////////////////////////////////////////////////////////////////


    // 이전 강의에서 사용한 테스트
    @Test
    public void springJdbcConnectionTest() throws Exception {
        // @Autowired DataSource ds;   랑 RunWith~~, @ContextConfiguration 만 있으면 아래 2줄도 필요가 없다.

        // ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        // DataSource ds = ac.getBean(DataSource.class);


        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn != null);           // assertTrue()  -> 괄호 안의 조건식이 true 이면 성공, false 면 실패!
                                                    // 즉, 여기선 getConnection() 을 제대로 가져오면, null 이 아니기에 성공!
    }
}