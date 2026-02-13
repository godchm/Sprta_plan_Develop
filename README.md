# 🗓️ 일정 관리 앱 Develop
## 📌 프로젝트 정보
| 항목       | 내용       |
|----------|----------|
| 이름       | 최형민      |
| 프로젝트명    | 일정 관리 앱 Develop |
| 버전       | v1.0.0   |
| Base URL | `http://localhost:8080/`|

## ✏️ 프로그램 소개
일정 관리앱은 회원가입, 로그인, 로그아웃을 활용합니다. 또 나만의 일정을 관리하고 시간 배분을 잘할수 있도록 도와주는 앱입니다. 이를 통해서 자신만의 루틴을 만들어봐요. 댓글기능을 통해서 지인들과 소통도 지원합니다.

## 주요 기능
### 👥 사용자
- 회원가입 - 유저이름 ,이메일, 비밀번호 필요.
- 로그인/로그아웃 - 이메일, 비밀번호 필요.
- 일정 등록 - 로그인 필요.
- 수정/삭제 - 로그인 필요. 유저를 삭제시 일정, 댓글도 삭제.

### 📘 일정 관리
- 일정을 작성을 통해서 자신의 일정 관리.
- 일정을 수정할시 수정시간 최신화.
- 일정을 삭제시 댓글도 자동 삭제

### 🗣️댓글
- 댓글 기능 - 참여자 간의 소통작용.

### ⚠️ 제한
- 유저 회원가입
    - 유저명은 필수 
    - 이메일은 필수 
    - 비밀번호 필수 및 8자 이상
  
- 유저 정보 수정
    - 먼저 로그인이 필요. 
    - 유저명 필수 
    - 이메일은 필수
  
- 일정 생성, 수정, 삭제.
    - 제목 필수 
    - 내용은 최소 30자 이상 
  

- 댓글 생성, 수정, 삭제 .
    - 최소 15자 이상 

## 🔧 기술 스택
| 구분 | 기술 | 버전 |
|-----|-----|-----|
| Language | Java | 17 |
| Framework | Spring Boot | 4.0.2 |
| Build Tool | Gradle | 9.3.0 |
| Database | MySQL | 8.4.8 |

## ⚙️ 설치 및 실행
### 1. 프로젝트 클론

```bash
git clone https://github.com/godchm/Sprta_plan_Develop.git
cd Sprta_plan_Develop
```
### 2.데이터베이스 설정
```bash
// MySQL에서 데이터베이스 생성
// 애플리케이션 실행 시 JPA(Hibernate)가 테이블을 자동 생성합니다.
CREATE DATABASE plan CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
### 3.애플리케이션 설정
```bash
src/main/resources/application.properties 파일에서 데이터베이스 정보를 설정.

spring.datasource.url=jdbc:mysql://localhost:3306/plan
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

### 4.애플리케이션 실행
#### Gradle 실행
```bash
./gradlew bootRun
```
#### 또는 JAR 파일 실행
```bash
./gradlew build
java -jar build/libs/Sprta_plan-0.0.1-SNAPSHOT.jar
```
# 일정 관리 Develop API 명세서

## 👤 User API

### 🔐 인증 API

### 🔹 회원가입

**POST** `/users/register`

Body

```json
{
  "username": "최형민",
  "useremail": "sersfafs123@naver.com",
  "password": "12341234"
}
```
### 🔹 로그인 

**POST** `/users/login`

```json
{
  "useremail": "sersfafs123@naver.com",
  "password": "12341234"
}
```

### 🔹 로그아웃

**POST** `/users/logout`

### 🔹 유저 전체 조회

**GET** `/users`

### 🔹 유저 단건 조회

**GET** `/users/{userId}`

### 🔹 유저 수정

**PUT** `/users/{userId}`

Body

```json
{
  "username": "갓형민",
  "useremail": "scasd@naver.com"
}
```

### 🔹 유저 삭제

**DELETE** `/users/{userId}`

---

## 📅 Plan API (일정)

### 🔹 일정 생성

**POST** `/users/{userId}/plans`

Body

```json
{
  "title":"나는 할수 있다. 3월 7일 결전의 날!!!!",
  "content":"결전의 날... 3월 7일 어떤 날들이 기다리고 있을까 많은 생각이 든다. 나는 여기 아직 살아있다. "
}
```

### 🔹 일정 전체 조회

**GET** `/users/{userId}/plans`

### 🔹 일정 단건 조회

**GET** `/users/{userId}/plans/{planId}`

### 🔹 일정 수정

**PUT** `/users/{userId}/plans/{planId}`

Body

```json
{
  "title": "나의 일정은 3월 10일이였네.. 원래 ",
  "content": "결전의 날은 3월 10일이였네 하지만 뭐 달라진건 없다. "
}
```

### 🔹 일정 삭제

**DELETE** `/users/{userId}/plans/{planId}`


---

## 💬 Comment API (댓글)

### 🔹 댓글 생성

**POST** `/users/{userId}/plans/{planId}/comments`

Body

```json
{
  "content": "그때 뭔날임? 뭐 준비함? 준비할거면 나랑 같이하지 지 혼자만 하네"
}
```

### 🔹 댓글 전체 조회

**GET** `/users/{userId}/plans/{planId}/comments`

### 🔹 댓글 단건 조회

**GET** `/users/{userId}/plans/{planId}/comments/{commentId}`

### 🔹 댓글 수정

**PUT** `/users/{userId}/plans/{planId}/comments/{commentId}`

Body

```json
{
  "content": "나랑 같이하자고 바로 연락해라 형은 참지 않아.."
}
```

### 🔹 댓글 삭제

**DELETE** `/users/{userId}/plans/{planId}/comments/{commentId}`

# ERD
<img width="826" height="1472" alt="ERD_Develop" src="https://github.com/user-attachments/assets/283ce3bd-72b8-4b40-8a6d-2d150b9ad434" />

# 일정 관리 Develop 구현 상황

### 2026.02.11 15:28
**구현 기능**
1. 필수 완료.

**구현 해야 하는 기능**
- 도전 기능 시작하기.

**특이사항**
-필수 기능 오류 수정 중. 

### 2026.02.11 16:32
**구현 기능**
1. 도전 Lv 5 초안 완성. 

**구현 해야 하는 기능**
- 예외처리 기능 추가하기. 

**특이사항**
필수 기능 오류 수정 중.




### 2026.02.11 16:55

**구현 기능**
1. 도전 Lv 5 완성.

**구현 해야 하는 기능**
- 도전 Lv 6 비밀번호 암호화 

**특이사항**
도전 Lv 6 비밀번호 암호화는 배운적이 없어서 구글링과 AI를 통해 학습 후 사용해야함. 

### 2026.02.11 15:21
**구현 기능**
1. 도전 Lv 6 완성.
2. 예외처리부분 수정 

**구현 해야 하는 기능**
- 도전 Lv 7 댓글 CRUD 구현

**특이사항**

### 2026.02.11 20:46
**구현 기능**
1. 도전 Lv 7 기능 구현중.

**구현 해야 하는 기능**
- 도전 Lv 7 댓글 CRUD

**특이사항**
엔티티가 3개가 되고, 단방향 매핑을 해줘햐하니 생각할점들이 많다. 복잡하다..


### 2026.02.12 12:51
**구현 기능**
1. 도전 Lv 7 초안완성.

**구현 해야 하는 기능**
- 지금까지 진행 한 프로젝트 오류 수정하기 

**특이사항**
프로젝트 변수명들을 수정해야할 필요가 있다. 


### 2026.02.12 15:13
**구현 기능**
1. 도전 Lv 8 진행중...

**구현 해야 하는 기능**
- 지금까지 한 프로젝트 코드 다듬기.
- 도전 Lv 8 페이징 기능.. 

**특이사항**
페이징 기능을 배운적이 없어서 생소하다...


### 2026.02.12 20:08
**구현 기능**
1. 도전 Lv 8 페이징 파일 생성..

**구현 해야 하는 기능**
- 도전 Lv 8 페이징 기능 구현..

**특이사항**
페이징 기능을 봐도 아직도 잘 이해가 안간다.


### 2026.02.13 11:35
**구현 기능**
1. 유저, 일정, 댓글 삭제 기능.

**구현 해야 하는 기능**
- 오류 찾기

**특이사항**
페이징 기능을 봐도 아직도 잘 이해가 안간다.



## 프로젝트💭 회고






