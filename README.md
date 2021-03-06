MY BOARD
=====================
## 개요
이 프로젝트는 계층구조 게시판을 구현한 프로젝트입니다.

## 프로젝트 구성
* MVC 패턴
* DB연동(MyBatis)
* 암호화(SHA-256)
* 로그인 적용
* Cookie를 통한 아이디 저장
* 페이지네이션 적용
* 계층구조의 게시판(답글)
* 게시판 관리 기능(게시판 CRUD)
* 댓글 기능
* 스마트에디터 적용(SE2)
* Maven 프로젝트
* 파일업로드
* el, jstl

## 상세 내용
* MVC 패턴
* DB연동(MyBatis)
    * mybatis 환경설정
    * userDao, replyDao, postDao, boardDao - mybatis 적용
* 암호화(SHA-256)
    * 회원가입, 로그인 시 암호화를 통한 보안강화
    * 단방향 암호화인 SHA-256을 사용함으로써 관리자도 조회가 힘듬(보안성 강화)
* 로그인 적용
    * 로그인을 통해 회원들만 이용가능함
* Cookie를 통한 아이디 저장
    * js.cookie.js 적용
    * serverside cookie 설정
* 페이지네이션 적용
    * next버튼, prev버튼 적용
    * 1페이지당 10개의 게시물이 보여지도록 페이지네이션 적용
* 계층구조의 게시판(답글)
    * 화면단에 계층구조로 표현
    * DB에 계층구조 적용
* 게시판 관리 기능(게시판 CRUD)
    * 게시판 관리 메뉴를 통해 게시판 관리 가능
* 댓글 기능
    * 게시글 상세정보에 댓글 등록 가능
    * CRUD 가능
* 스마트에디터 적용(SE2)
    * 네이버 제공해주는 SE2를 통해 게시글 작성, 수정
    * 파일업로드
* el, jstl
* logback 라이브러리 추가
    * sysout 로직 logger로 개선
* url
    * WEB-INF 외부에서 접근이 안됨
* JUnit을 이용한 단위 테스트 적용

   
## Author
* Bumhwi Kim (김범휘) : https://github.com/bumpercar93
* email : youbi89@naver.com
