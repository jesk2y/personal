## Basic Board - MyBatis


## Stacks

**Environment**
<div>
  <img src="https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white"> 
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>
<br>

**Development**
<div> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/MyBatis-000000?style=for-the-badge&logo=MyBatis&logoColor=white"> 
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
  <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
  <br>
</div>  
<br>

**Front**
<div>
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white">
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/Axios-5A29E4.svg?style=for-the-badge&logo=Axios&logoColor=white"> 
</div>

<br>

## 프로젝트의 목적
> 단순히 Spring Legacy에서 Sping Boot로 넘어가며 자연스레 보편적인 라이브러리를 사용하는 것 대신, Spring Legacy에서 사용한 기능들을 Spring Boot에서도 사용해 보며 Spring Legacy와 Spring Boot의 차이를 알아본다.
> 
> 또한 아직까지 Spring Legacy에서 사용되고 있는 MyBatis로 게시판의 기본적인 CRUD 및 검색, 페이징, 파일 업로드, 회원가입을 처리하고 프로젝트에서 발생하는 예외를 사용자 맞춤으로 처리하는 방식을 실습한다.
> 
> 또한 Spring Boot와 주로 같이 사용되는 Spring Security를 사용하기 전 Interceptor로 로그인 및 보안 처리를 하는 방법을 실습한다.

<br>

## 게시판의 기능
- 기본적인 CRUD
- 글 검색/페이징
- 댓글/대댓글(1단계) 기능
- 댓글 페이징 기능
- 이미지 첨부 기능
- DB에 존재하지 않는 이미지를 서버에서 삭제하는 기능
- 회원가입/로그인/회원탈퇴 기능 (Spring Security 미사용)
- 비밀번호 암호화 기능
- 비밀번호 변경 기능
- 커스텀 에러 페이지 적용
- 서버 단에서 권한이 없는 사용자의 접근을 막는 기능

<br>

## 페이지
|설명|페이지|설명|페이지|
|:---|:---|:---|:---|
|리스트|![image](https://github.com/jesk2y/personal/assets/41985737/9c5350a4-f40f-40ae-8db0-7757af5cb14d)|내용|![image](https://github.com/jesk2y/personal/assets/41985737/eb68fc69-8faa-4a58-84e7-d5b2fc3e399d)|
|글 등록/글 수정|![image](https://github.com/jesk2y/personal/assets/41985737/e757c48a-ebd1-45c2-8f5a-391eaa6ac11b)|파일 업로드|![image](https://github.com/jesk2y/personal/assets/41985737/af9ef8d8-7431-4bce-9aa8-f03ad7d06258)|
|파일 업로드 2|![image](https://github.com/jesk2y/personal/assets/41985737/49ba04fb-6994-4f84-b59e-9d036869bea5)|댓글|![image](https://github.com/jesk2y/personal/assets/41985737/187f333a-ec0c-45f3-8e53-4f4faff1f63c)|
|로그인|![image](https://github.com/jesk2y/personal/assets/41985737/68dc4422-c2b2-431c-bec4-7bd10462af9b)|회원가입|![image](https://github.com/jesk2y/personal/assets/41985737/39d64120-be4b-412e-be17-7f2240f3f469)|
|회원정보|![image](https://github.com/jesk2y/personal/assets/41985737/9ceaaf8d-f4aa-41f2-ae81-ef0c339944bd)|비밀번호 변경|![image](https://github.com/jesk2y/personal/assets/41985737/927c2064-a2ce-46e6-a561-360432f38357)|
