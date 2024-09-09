# 🏋️ 홈 PT 전문가 매칭 사이트 '방구석 운동왕'

![스크린샷 2024-05-02 234346](https://github.com/user-attachments/assets/2b003878-ccd8-47b5-9cf5-9536d0d51d3d)


멋쟁이 사자처럼 백엔드 스쿨 8기에서 진행한 2차 종합 프로젝트 입니다.

<br>

## 👋 프로젝트 소개

운동 전문가가 집으로 방문하여 운동 프로그램을 제공하도록 매칭 해주는 플랫폼입니다.
PT, 요가, 산전/산후 관리 등 여러 카테고리의 프로그램을 제공합니다.

<br>

## 📆 일정

**기간: 2024.04.11 ~ 2024.05.03**

<br>

## 1. 개발 환경

- Back-end : Java 17, Spring Boot 3.2.4, Spring Data JPA, Spring Security, Spring Mail, JWT, OAuth2, Toss
- Front-end : Javascript, HTML/CSS, Thymeleaf, BootStrap
- Database : MySQL, H2, Redis
- infra : docker, NGiNX, NCP
- tool : Notion, google docs, GitHub
- design : [피그마](https://www.figma.com/file/afOtUDAOXdUprqBZb2d72y/%EB%B0%A9%EA%B5%AC%EC%84%9D-%EC%9A%B4%EB%8F%99%EC%99%95(%EA%B0%80%EC%A0%9C)?type=whiteboard&node-id=0-1&t=ZF4kqZLU1AsZONO9-0)

<br>

## 2. 주요 기능
![image](https://github.com/user-attachments/assets/0f0e8c0c-d1d3-41d1-9ff7-1feb6728a837)


### 사용자 인증 및 권한 처리

- **인증 방식**
    - JWT 토큰을 활용한 인증
- **유저**
    - 카카오 로그인을 통해 회원가입, 로그인
    - 로그인 하지 않은 사용자는 메인 페이지와 강사 소개 페이지를 볼 수 있음
    - 일반 사용자는 수업을 신청하고 결제할 수 있음
    - 회원 탈퇴 후 탈퇴 유저에 등록 후 유저 정보 보관
- **강사**
    - 강사 회원가입을 진행하면 관리자가 강사를 검증한 뒤 신청을 승인
    - 강사 페이지에서 수업과 수강생을 관리
- **관리자**
    - 모든 권한을 가진다.

### 유저의 수업 신청 및 결제

- **수업 신청**
    - 강사의 프로그램을 검색하여 신청
    - 가능한 일정 선택 후 결제
    - 강사가 수업을 수락하면 매칭 완료, 거절하면 결제 취소
- **회원 페이지**
    - 수업 찜(즐겨찾기) 기능
    - 수업 정보 확인
    - 수업 후기 작성(별점, 사진)

### 강사의 수업 생성 및 관리

- **수업**
    - 수업 생성, 수정, 삭제 신청이 가능(관리자의 최종 승인 필요)
    - 회원 목록 확인, 수업 일지 작성(수업일지 작성 시 회원의 회원권에서 1회 차감)
    - 후기 확인 및 답글 작성
    - 수업 수익 정산 신청(관리자 확인 후 계좌로 입금)
    - 가능한 지역과 시간대를 설정하여 검색 필터 등록

### 관리자의 승인 및 관리

- 유저, 강사, 수업의 목록과 정보 확인
- 수익 확인, 메달 수여, 정산 지급
- 회원가입 및 탈퇴, 수업 생성, 수정, 삭제 승인
- 카카오 채널 api를 사용해 1:1 채팅으로 문의를 해결
<br>


## 3. 주요 기능 시연

### ✅메인 화면

![메인_새로고침](https://github.com/user-attachments/assets/64f14f1a-de1b-41e4-b157-c634cf938eac)

- 메인 화면에서는 인기 강사와 회원의 후기를 볼 수 있습니다.
- 강사는 수익에 따라 메달을 부여받으며, Gold 메달인 강사가 메인에 랜덤으로 추천됩니다.
- 회원 후기는 별점이 높은 순으로 나열됩니다.
<br>

### ✅유저 로그인, 마이 페이지

![유저카카오로그인_찜목록](https://github.com/user-attachments/assets/37a375bb-a08f-4a59-8660-7e453eb3b314)


- 카카오 로그인 API를 통해 회원가입 및 로그인합니다.
- 로그인 후 유저 페이지에서는 수업 목록과 찜 목록을 볼 수 있습니다.
- 하트를 눌러 찜 목록에 들어가 있는 수업을 확인하고 삭제할 수 있습니다.
<br>

### ✅유저 - 후기 작성

![유저_후기작성](https://github.com/user-attachments/assets/dbbf6979-b3f0-4900-9dae-dcabccb8f801)


- 내 수업 목록에서 아직 작성하지 않은 후기를 작성할 수 있습니다.
- 후기 사진을 올릴 수 있으며, 별점을 줄 수 있습니다.
- 별점은 강사의 점수에 반영됩니다.
<br>

### ✅유저 - 강사 매칭

![유저_강사매칭](https://github.com/user-attachments/assets/63b17e19-1dc5-4a18-85ca-26cf616261a5)

- 유저는 지역과 카테고리를 설정하여 강사와 매칭할 수 있습니다.
- 강사의 정보와 프로그램, 가능한 시간을 확인하고 강사에게 작성된 회원 후기를 볼 수 있습니다.
<br>

### ✅유저 - 수업 신청 및 결제

![유저_결제](https://github.com/user-attachments/assets/a55aa74b-5463-4382-8fb8-2b4bbae08e49)

- 유저는 가능한 요일, 시간대와 회차권을 선택하고 수업을 신청할 수 있습니다.
- Toss Payment API를 통해 결제 시스템을 구현하였습니다.
<br>

### ✅강사 회원 등록 신청

![강사회원가입_이메일 인증](https://github.com/user-attachments/assets/754749d8-be66-4a74-ac37-a1b8dfe012be)


- 강사 회원가입은 관리자의 강사 검증이 필요하기 때문에 일반 회원가입으로 구현하였습니다.
- 강사가 회원 등록을 신청하면 관리자가 따로 이메일을 보내 경력과 자격증 증명, 범죄이력 등을 확인한 후 회원가입을 승인합니다.
- Redis로 이메일 인증을 구현하였습니다.
<br>

### ✅강사 회원 등록 신청 - 프로필 사진 등록

![강사회원가입_프로필](https://github.com/user-attachments/assets/86220f51-d81f-430f-9751-5a8f07e9ddbd)


- 강사는 프로필 사진을 등록할 수 있고, 유저에게 공개됩니다.
<br>

### ✅강사 로그인, 마이 페이지

![강사_로그인](https://github.com/user-attachments/assets/b13610e6-8061-478b-a476-5918e76104fe)

- 아이디 비밀번호로 로그인합니다.
- 마이페이지에서 수업, 유저 등을 관리할 수 있습니다.
<br>

### ✅강사 - 수업 생성

![강사_프로그램 생성](https://github.com/user-attachments/assets/8236db10-9824-436a-8aca-304e049ea977)


- 강사가 수업을 생성하면 관리자에게 신청이 전달되고, 관리자 승인 후 최종 생성됩니다.
<br>

### ✅강사 - 수업 수정

![강사_프로그램 수정](https://github.com/user-attachments/assets/7d6d0e2a-ea08-4f18-a05e-c61bf6a9693c)

- 수업 수정, 삭제 신청이 가능합니다.
<br>

### ✅강사 - 수업 일지 작성

![강사_수업일지작성](https://github.com/user-attachments/assets/8c6a7a80-e890-46b4-8ff4-bbc876aab4f7)


- 강사는 수업 후 수업일지를 작성합니다. 수업 일지가 작성되면 회원의 회차권에서 1회가 차감됩니다.
- 회차가 모두 완료되면 수업 완료한 회원 목록으로 이동시킵니다.
<br>

### ✅강사 - 스케줄 관리

![강사_스케줄관리](https://github.com/user-attachments/assets/a6ab79ed-846a-44e2-a5c8-021c74953504)


- 강사는 가능한 지역과 시간을 저장할 수 있습니다. 강사 매칭 시 필터에 반영됩니다.
<br>

### ✅강사 - 리뷰 답글

![강사_리뷰답글](https://github.com/user-attachments/assets/4d45969a-c1cf-4235-99a8-2035abf5b898)

- 유저에게 받은 리뷰를 모아 볼 수 있고, 답글을 작성, 수정, 삭제할 수 있습니다.
<br>

### ✅강사 - 정산 신청

![강사_정산신청](https://github.com/user-attachments/assets/477c458e-fdaf-4aa9-8281-22d4ec82ad43)


- 유저가 결제한 금액은 강사의 정산금에 저장됩니다.
- 강사는 원하는만큼 정산 신청을 할 수 있습니다.
- 관리자가 확인 후 계좌에 입금하고 정산금 지급을 완료합니다.
<br>

### ✅강사 - 정보 수정 및 탈퇴 신청

![강사_정보수정,탈퇴신청](https://github.com/user-attachments/assets/a20a732a-efad-4a2c-a0c8-8783cd2ae429)


- 강사는 프로필 정보를 수정하거나 탈퇴 신청을 할 수 있습니다.
<br>

### ✅강사 - 유저 신청 수락

![강사_유저신청수락](https://github.com/user-attachments/assets/5b3878a2-6976-4e68-8e86-c51031057a4a)


- 유저의 수업 신청을 확인하고 카카오톡을 통해 연락하여 세부 사항을 논의할 뒤 수락하거나 거절할 수 있습니다.
- 강사가 승인한다면 수업의 유저목록에 추가되고, 거절한다면 유저의 결제가 환불됩니다.
<br>

### ✅관리자 - 로그인 및 메인 페이지

![admin_메인](https://github.com/user-attachments/assets/eaa668c2-2eb0-4b87-aaf0-9b48e6daf164)


- 주어진 아이디와 비밀번호로 로그인 합니다.
- 메인 페이지에서 유저와 강사 목록을 확인할 수 있습니다.
<br>

### ✅관리자 - 유저 관리, 환불

![admin_유저환불](https://github.com/user-attachments/assets/54c4ad9e-fd6d-4a33-bd47-fe89231e840e)


- 유저가 결제한 회차권을 부분, 전액 환불하거나 회차를 수정할 수 있습니다.
- 카카오 1:1 채팅을 통해 유저의 문의를 받고 환불을 진행하게 됩니다.
- 관리자가 금액만큼 입금한 뒤 사이트에서 회차권을 수정, 삭제합니다.
<br>

### ✅관리자 - 강사 관리, 메달

![admin_강사메달](https://github.com/user-attachments/assets/e49ebcac-9d26-4726-b148-86659565bc3a)


- 강사 정보(매출, 평점 등)를 확인하고 강사에게 메달을 수여할 수 있습니다.
- Gold 메달의 경우 메인 페이지에 인기 강사로 소개됩니다.
<br>

### ✅관리자 - 승인 관리

![admin_승인](https://github.com/user-attachments/assets/f55e04c5-db89-468f-b8f7-0d8e4341aba2)


- 강사 신청, 회원 탈퇴 등을 승인, 반려합니다.
<br>

### ✅관리자 - 프로그램 승인 관리

![admin_프로그램승인](https://github.com/user-attachments/assets/ad406ad1-8a72-4265-aac9-af3644cad2d2)


- 프로그램 신청, 수정, 삭제를 승인, 반려합니다.
<br>

### ✅관리자 - 정산 관리

![admin_정산](https://github.com/user-attachments/assets/5e70463b-6460-4580-97f3-1c8bc5b60136)


- 강사의 정산 신청을 볼 수 있고, 정산금을 입금한 뒤 확인합니다.
<br>


### ✅카카오채널 1:1 채팅
![image](https://github.com/user-attachments/assets/dcf60c2f-db7c-4dd1-85ff-64b45a403e42)

- 카카오싱크를 통해 카카오 채널을 만들었습니다.
- 채털 메시지, 알림톡 등 다양한 기능을 사용할 수 있습니다.
- 1:1 채팅으로 유저의 상담 문의에 답변할 수 있습니다.

<br>
<br>

## 4. 프로젝트 진행 과정

### 💡배포 과정

![image](https://github.com/user-attachments/assets/c491fbf1-fcf2-4819-87f6-0a516151b927)

- 도커와 MySql, redis를 AWS EC2에 올려서 배포를 진행했습니다.
- Docker network로 각각의 컨테이너를 같은 네트워크로 묶어주었습니다.
- 서버가 계속 다운되는 오류가 있었는데 메모리 용량 때문이었고, NCP로 변경하여 진행했습니다.

<br>

![image](https://github.com/user-attachments/assets/fb04cda5-a1f3-4cba-a670-2640f821305c)

- 도메인과 HTTPS 설정을 해주고 https://homegym.site 로 접속할 수 있었습니다.
<br>



### 💡 ERD

![image](https://github.com/user-attachments/assets/e2738307-ea0a-4e58-af91-8af656f9bb62)
<br>

### 💡 개발 일정
![image](https://github.com/user-attachments/assets/bf7f9d15-0261-4cbc-a111-e6045d7997a1)
![image](https://github.com/user-attachments/assets/799a11d1-e4f7-4926-b693-66ceaed39a8e)
<br>
