# 🎲 요기다이스(YOGIDICE)
빅데이터를 활용한 보드게임 추천 서비스
<hr>
![로고](/uploads/7e4a2fd00303d1aaf851dababba25275/로고.png)

## ♟프로젝트 소개

### 🗓프로젝트 일정
2022.08.22(월) ~ 2022.10.07(금) (총 7주)

### 👊팀


| 김현주 | 김동신 | 박정현 | 전병찬 |최원재|
| :---: | :---: | :---: | :---: |  :---: |
|<img src="https://lab.ssafy.com/s07-bigdata-recom-sub2/S07P22B206/uploads/29e8a97f6b79fae410834d405fd69c8a/현주킴.jpg" width="120" height="120">|<img src="https://lab.ssafy.com/s07-bigdata-recom-sub2/S07P22B206/uploads/836ebfa6bd537648164d6a7cac19e123/김똥신.jpg" width="120" height="120">|<img src="https://lab.ssafy.com/s07-bigdata-recom-sub2/S07P22B206/uploads/c9a7d3e1295e94d5b76c1fd3ef272cd1/빡정현.jpg" width="120" height="120">|<img src="https://lab.ssafy.com/s07-bigdata-recom-sub2/S07P22B206/uploads/5a444b6e1092dd7c275399ce12cae3f7/쩐병찬.jpg" width="120" height="120">|<img src="https://lab.ssafy.com/s07-bigdata-recom-sub2/S07P22B206/uploads/e62ac6a0fa0c592b8d9e6fcb824286f3/원재.jpg" width="120" height="120">|
|팀장,<br/>프론트 구현,<br/>데이터분석서버설계|프론트 핵심 구현,<br/> UI 설계|DB 설계,<br/>데이터 크롤링,<br/>추천 알고리즘 설계|백엔드,<br/>DB 설계,<br/>REST API|백엔드,<br/>인프라,<br/>배포(CI/CD)|

### 🔔목표
기획의도와 일치하는 기능을 효율적인 구조로 구현하고 CI/CD 개념을 적용한 배포까지 완료하기! 

### 🔍기획의도 
보드게임 카페에 가면 마주치는 수많은 보드게임들... 많은 게임들 중 내가 좋아할 만한 게임인지 알 수 없어서<br/> 항상 하던 게임만 하게 되는 상황을 해결하고자 **사용자의 취향을 분석하여 게임을 추천하는 서비스** 개발을 기획하게 되었습니다.

### 🕹프로젝트 주요 기능 및 특징
- KNN 알고리즘을 활용한 사용자 취향 저격 게임 추천 기능
- Jaccard Similarity 알고리즘을 활용한 게임과 비슷한 다른 게임 추천 기능
- 플레이한 게임에 대한 리뷰 남기는 기능
- 자주 플레이한 게임을 쉽게 찾기 위한 북마크 기능
- 보드게임에 필요한 도구 제공 기능

### 🛠주요 기술

### 🖥배포 환경

### 📚프로젝트 기술 스택

- 기술 세부 내역

|구분|기술 스택|상세 내용|버전|
|:---:|:---:|:---:|:---:|
|**공통**|형상관리|GitLab|- |
| |이슈관리|Jira|- |
| |이슈관리|Notion|- |
|**프론트엔드**|HTML5| | |
| |CSS| | | 
| |JavaScript(ES6)| | |
| |node      | | 16.16.0| 
| |chartjS   | |4.1.1| 
| |Vue       | |3    |
| |   |Vuex|4.0.2| 
| |IDE| Visual Studio Code|1.80| 
|**백엔드**|Java|OpenJdk|1.8.0| 
| |Build|Gradle|7.5| 
| |      |Spring Boot|2.7.3|
| |      |Spring Security|2.7.3|
| |      |Pyhton|3.7.9|  
|**백엔드(추천 알고리즘)**|      |Django|3.2.12|  
| |IDE| Visual Studio Code|1.70| 
| |API Docs|Swagger|3.0.0|
| |DB|MySQL|8.0.29|
| | |Spring-Data-jpa|2.7.3|
|**서버**|AWS EC2|GNU/Linux|5.4.0-1018|
|    |       |Ubuntu   |20.04.5 LTS|
||배포|Docker|20.10.18|
|||jenkins|2.361.1|
||웹 서버|Nginx|1.23.1|  


## 🖋기획/설계
### 📑[기능 요구 명세서](https://www.notion.so/ac00c0280b464f87897662986e957bab)
 필요한 기능과 기능에 대한 내용, 기능이 들어갈 페이지, 기능의 완료 여부를 정리하였습니다.
![기능_명세서900](/uploads/e82ee99f3a17839fdfdcabaff9803dd4/기능_명세서900.png)

### 🎨[와이어 프레임(피그마)](https://www.figma.com/file/x4lH98VHe6W78rUXCxuXUg/%ED%8A%B9%ED%99%94PJT-B206?node-id=175%3A3)
![피그마900](/uploads/08231fd39f7ce65088f736140ba43ab1/피그마900.png)

### 📁[ERD](https://www.erdcloud.com/d/5CpJdWqcK5Fg8Phaa)
![erd900](/uploads/997ee460dee2c92ec841335356bfa2ac/erd900.png)


### 🌫서비스 플로우

## 프로젝트 관리/운영 
### 디렉토리 구조
|Spring Boot|Django|Vue|
|:---:|:---:|:---:|
|![스프링디렉토리](/uploads/733d627099c8ab9d83d13ad7a527fe86/스프링디렉토리.png)|![장고디렉토리리얼](/uploads/40bef83c5253310ae73f9733051e2a7c/장고디렉토리리얼.png)|![뷰디렉토리](/uploads/c48e6207319d152ab57f5be0342d885e/뷰디렉토리.png)|

### GitLab
![깃1-900](/uploads/58e27a29d13984f47ecba0a3274506e8/깃1-900.png)
![깃2진-900](/uploads/1f1e33c5bfc60e6566ca00bfd8f5e97c/깃2진-900.png)

### Jira
- **스프린트**
<br/>
![지라900](/uploads/0083f894f0669317e3d747906fb501f2/지라900.png)
<br/>
<br/>

- **번다운 차트**
<br/>
![번다운_차트900](/uploads/7eb6173ec3f858c8130e563c9e27466c/번다운_차트900.png)


### Notion
![노션1](/uploads/a3a06b1d50d1d7eb831360d539edde84/노션1.png)
![노션2](/uploads/b5f2ba9823d6d2fd0e1cb63210a0741f/노션2.png)


### 스크럼 미팅
![스크럼900](/uploads/98e58d35db30a038f7f102049bae0d6e/스크럼900.png)

## 🎮 서비스 구현 

### ⚙주요 기능

<hr/>

#### 🎰게임 상세 정보 제공

#### 🎯취향 저격 추천

#### 👍비슷한 게임 추천

#### 🕹필터링 추천

#### 🗺주변 보드게임 카페 추천

#### 📌북마크, 리뷰 기능
|북마크|리뷰|
|:---:|:---:|

#### 🪀게임 도구 

|주사위|타이머|술래 정하기|벌칙 정하기|
|:---:|:---:|:---:|:---:|

## 🥂프로젝트 회고

### 김현주

### 김동신

### 박정현

### 전병찬

### 최원재






