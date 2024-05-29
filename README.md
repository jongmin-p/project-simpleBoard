# Personal Project (개인 프로젝트)
-----


# 1. 개요
* 프로젝트 : CRUD 를 이용한 게시판

* 일정 : 2024. 05. 13 ~

* 목적 : 학습 중인 스프링 프레임워크를 복습하고자 진행중<br/><br/>
### 1-1. 사용 기술 및 개발환경
   - Server : Tomcat9.0
   - DB : MySQL
   - Framework/flatform : Spring, jQuery, MyBatis
   - Programming Language : Java, HTML/CSS, JavaScript
   - Tool : IDE(IntelliJ), Github<br/><br/>
   
### 1-2. 구현 기능
- 회원가입
   - 아이디, 비밀번호, 이름 등 검증하여 조건에 만족하지 않을 때는 가입 비활성화

- 로그인
   - 아이디, 비밀번호를 검증하여 조건에 만족하지 않을 때는 로그인 비활성화
   - '아이디 기억하기' 체크 후 성공적으로 로그인 시, 다음에 로그인 화면으로 돌아오면 아이디가 자동 입력돼 있음

- 게시판 페이지
   - 우선 메인 페이지에서 게시판 클릭 시, 로그인하지 않은 상태라면 로그인 먼저 하도록 함
   - 로그인 된 상태라면, 게시판 클릭 시 전체 게시글 조회 출력
   - 페이징 기능 적용
   
- 게시글
   - 모든 회원은 게시글을 생성/조회/수정/삭제 할 수 있다
   - 해당 게시글을 작성한 회원 본인만 수정과 삭제를 할 수 있다
   - 모든 게시글에는 조회수 기능이 구현돼 있다

- 댓글
   - 모든 회원은 댓글을 생성/조회/수정/삭제 할 수 있다
   - 해당 게시글을 작성한 회원 본인만 수정과 삭제를 할 수 있다<br/><br/>
   
### 1-3. 추후에 구현할 기능
- 마이 페이지 (본인이 작성한 게시글 조회 등)
   - 본인 회원 정보 변경
   - 본인이 작성한 글 조회

- ID/PW 찾기

- 파일 업로드

- 관리자 페이지
  - 회원 테이블(user) 에 존재하는 컬럼들(status, lastDate 등) 을 이용하여 최근 접속 기록 / 회원 탈퇴 여부 등 정보 확인
  - 게시글 테이블(board) 에 존재하는 컬럼들(status, deleteDate 등) 을 이용하여 게시글 삭제 날짜 / 수정 날짜 등 확인<br/><br/><br/>


# 2. DB 테이블
### 2-1. 회원 테이블 (user)
| 컬럼명         	| 데이터 타입     	| 컬럼 설명                   	|
|----------------	|----------	|--------------------------	|
| userId        	| varchar  	| 아이디                 	|
| userPwd        	| varchar  	| 비밀번호                 	|
| userName       	| varchar  	| 이름                     	|
| userPhone      	| varchar  	| 연락처                   	|
| userEmail      	| varchar  	| 이메일                   	|
| status         	| char     	| 탈퇴 여부                	|
| isUser         	| char     	| 유저인지 관리자인지 확인 	|
| regDate        	| datetime 	| 가입 날짜                	|
| lastDate       	| datetime 	| 최근 접속 날짜           	|
| withdrawalDate 	| datetime 	| 탈퇴 날짜                	|

### 2-2. 게시글 테이블 (board)
| 컬럼명         	| 데이터 타입     	| 컬럼 설명           |
|------------	|----------	|-------------	|
| boardNo      	| int  	           | 게시글 번호 	|
| title      	| varchar  	| 게시글 제목 	|
| content    	| text     	| 게시글 내용 	|
| writer     	| varchar  	| 작성자      	|
| viewCnt    	| int      	| 조회수      	|
| commentCnt 	| int      	| 댓글 수     	|
| status     	| char     	| 삭제 여부   	|
| regDate    	| datetime 	| 등록 날짜   	|
| modDate    	| datetime 	| 수정 날짜   	|
| deleteDate 	| datetime 	| 삭 날짜   	|

### 2-3. 댓글 테이블 (comment)
| 컬럼명         	| 데이터 타입     	| 컬럼 설명           |
|------------	|----------	|----------------	|
| commentNo    	| int      	| 댓글 번호    	|
| boardNo    	| int      	| 게시글 번호    	|
| pCommentNo 	| int      	| 부모 댓글 번호 	|
| comment    	| text     	| 댓글 내용      	|
| writer     	| varchar  	| 작성자         	|
| status     	| char     	| 삭제 여부      	|
| regDate    	| datetime 	| 등록 날짜      	|
| modDate    	| datetime 	| 수정 날짜      	|
| deleteDate 	| datetime 	| 삭제 날짜      	|

<br/><br/>
# 3. 산출물
   ### 3-1. 회원 가입
   - 아이디 검증 (가입 버튼 비활성화 케이스)
      - 빈 칸 입력 시
      - 글자수 등 양식 위반 시
      - 이미 존재하는 아이디 입력 시<br/><br/>
![아이디 검증](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/7c635b34-0562-4cc5-ae73-42386d1fd3b2)

<br/><br/>

   - 비밀번호 검증 (가입 버튼 비활성화 케이스)
      - 비밀번호 양식 위반 시
      - 재확인 비밀번호 불일치 시<br/><br/>
![비밀번호 검증](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/62145d6d-8dc1-4d4f-892c-9a734e270ac1)

<br/><br/>

   - 회원가입 성공 (가입 버튼 활성화)
      - 메인 페이지로 이동<br/><br/>
![회원가입 성공](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/690b8ce3-10d1-4133-9836-659b58a7ee5e)

<br/><br/>

   ### 3-2. 로그인
   - 로그인 실패
      - 존재하지 않는 아이디를 입력하는 경우
      - 아이디는 존재하나, 비밀번호가 일치하는 경우<br/><br/>
![로그인 검증](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/29f1cc6b-b239-418c-ba41-bab260ae1c65)

<br/><br/>

   - 로그인 성공
      - 로그인 성공 시, 메인 페이지로 이동
      - 그리고, 우측 상단의 Login 버튼이 Logout 버튼으로 자동으로 변경<br/><br/>
![로그인 성공](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/f81c6493-8313-4d1f-9390-412a3440a1c0)

<br/><br/>

   - '아이디 기억하기' 체크 후, 로그인 성공 시 다음에 로그인 화면으로 돌아오면 자동으로 아이디가 입력돼 있음.<br/>
![아이디 저장 기능](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/333a8222-2420-4d3a-8e82-21ca544fa140)

<br/><br/>

   ### 3-3. 전체 게시판
   - 로그인하지 않은 상태에서 Board 탭 클릭 시, 로그인 페이지로 이동
      - 이 후 로그인 성공 시, Board 페이지로 이동<br/><br/>
   - Board 탭 클릭 시, 게시판 전체 출력
      - 게시글 조회수 출력
      - 페이징 적용<br/><br/>
![전체 게시판](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/06f2995e-8d27-4e97-8e1e-661983671739)

<br/><br/>

   ### 3-4. 게시글
   - '글쓰기' 버튼 클릭 시, 게시글 작성 가능
     - 게시글 작성 후 등록 버튼 클릭 시, 등록 성공 메시지와 함께 전체 게시판에 추가됨<br/><br/>
![게시글 생성](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/2221c309-e712-42e8-b532-ee407ee94548)

<br/><br/>

   - 특정 게시글 클릭 시, 조회 가능
      - 본인이 작성한 게시글이라면, 하단에 '수정', '삭제' 버튼 노출됨<br/><br/>
![게시글 읽기](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/1babf02a-74d9-4664-8482-8976bde0e3df)

<br/><br/>

   - 게시글 수정 버튼 클릭 시, 수정 가능
      - 게시글 작성자 본인만 수정 가능
      - readonly 해제를 이용하여 제목과 내용 변경 가능 (작성자는 자동으로 채워지는거라 변경 불가능)<br/><br/>
![게시글 수정](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/a689f060-9f62-4f96-8fdf-053ea62353e3)

<br/><br/>

   - 게시글 삭제 버튼 클릭 시, 삭제 가능
      - 게시글 작성자 본인만 삭제 가능
      - 삭제하기 전, 삭제 확인 메세지 출력<br/><br/>
![게시글 삭제](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/80d7bb5b-127e-4413-a7f6-2abe88c80a3e)

<br/><br/>

   ### 3-5. 댓글
   - 각 게시글 아래에는 댓글 란이 존재
      - 댓글 작성자 본인만 수정 및 삭제 가능<br/><br/>
![댓글 생성](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/433f943b-4aa0-4f5a-b909-91786cecf7af)

<br/><br/>

   - 댓글 수정 버튼 클릭 시, 팝업 창이 노출됨<br/><br/>
![댓글 수정](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/48b77cee-6cb2-4607-a452-7ea5691bb57a)

<br/><br/>
    
   - 댓글 삭제 버튼 클릭 시, 삭제됨<br/><br/>
![댓글 삭제](https://github.com/jongmin-p/project-simpleBoard/assets/119127039/dcb8b74d-bc68-40db-9a79-61fb7eab6239)
