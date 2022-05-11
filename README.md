# 조회용 Application Framework


## 개발 환경
 - 개발언어 : Java, json, html
 - 개발도구 : Spring Tool Suite 4
 - 프레임워크 : Spring Boot 2.6.4
 - 빌드도구 : Gradle
 - Database : H2 (in memory RDBMS)


## 프레임워크 아키텍처
![image](https://user-images.githubusercontent.com/101878657/159903390-8dc611e9-3b46-4722-b081-bd92f02cd91d.png)



## 설계 개념
### Front-End
 - 테스트 수행을 위한 HTML, javascript 기반 기본 웹 화면 제공
 - 공통 헤더부(CommonData)는 javascript 함수 기반 생성, 조립, 전달 (Client)
 - Input/Output은 JSON타입 통신으로 @RestContoller를 통한 전송
 - GUID는 31자리 값으로 '날짜 + 시간(ms3) + 업무코드 + 랜덤(7자리 정수)'로 Unique 채번

### Back-End
 - Spring Boot 프레임워크 기반 AOP를 활용하여 공통 프레임워크 로직을 모듈화
 - BizRestController 호출 시 @Around 어노테이션 통해 JoinPoint 앞 뒤로 프레임워크 Advice(공통코드) 호출 
 
 - 요청데이터 파싱, 검증, 헤더 분리, 로깅 / 응답데이터 조립, 로깅, 리턴 구조 프레임워크 설계
   - 요청데이터 파싱
   - ![image](https://user-images.githubusercontent.com/101878657/159862896-b8cbfae5-f296-4048-b744-f5d159b4aef9.png)

   - 요청데이터 검증
   - ![image](https://user-images.githubusercontent.com/101878657/159862958-5272ac00-a7eb-449a-be50-84b22c7295ee.png)

   - 요청데이터 헤더분리
   - ![image](https://user-images.githubusercontent.com/101878657/159862982-3a281bad-d35b-4b67-bd29-54063c1ac06d.png)

   - 요청데이터 로깅
   - ![image](https://user-images.githubusercontent.com/101878657/159863069-ddb32cd8-fa1f-4ed5-a1d7-51eca49b5a37.png)

   - 응답데이터 조립
   - ![image](https://user-images.githubusercontent.com/101878657/159863143-5a7305a2-c0d8-4f17-8923-693d0cca4f40.png)

   - 응답데이터 로깅
   - ![image](https://user-images.githubusercontent.com/101878657/159863094-29bb2c12-6623-444d-860b-465c07babd6c.png)

 - BIZ 담당자는 개발영역(BizRestController, Service, Repository)에서 Biz 서비스에만 집중
 
 - Framework 담당자는 프레임워크영역(Aspect, FrameworkController) 관리 및 거래로그 관리
 
 - BIZ 서비스 확장 필요시 BizRestController부분만 추가 개발로 확장성 구현
 
 - 데이터 변경 적은 특정 서비스 Cache 사용으로 DB 부하 감소
   (DB 변경에 따른 캐쉬 리프레시는 Update 서비스 통해 구현 가능)
 
 - @Transactional(readOnly=true) 통한 Biz 서비스 데이터 변경 제한
   ![image](https://user-images.githubusercontent.com/101878657/159893440-3644280c-af23-46ad-99a7-e72ec27c647d.png)
   
 - 내부전문 데이터구조는 HashMap 기반 SData(단건)와 ArrayList 기반 SMultiData(다건)으로 설계
 
 - 업무코드(BizCode) GUID 내부 채번 및 코드 검증 통한 업무 구분 처리
 - ![image](https://user-images.githubusercontent.com/101878657/159861993-087fac46-85c5-4432-95a5-24692a069856.png)

 - 에러코드(ErrorCode) 일괄 관리 및 FrameworkException 통한 예외처리 핸들링
 - ![image](https://user-images.githubusercontent.com/101878657/159862027-19dd0817-3462-44ef-968f-6a0f8455f562.png)
 - ![image](https://user-images.githubusercontent.com/101878657/159862150-df2e1f18-2ce6-497c-b2a4-7f663221d6bf.png)


## DataBase 구성
![image](https://user-images.githubusercontent.com/101878657/159878947-f407dc30-cdeb-4b0c-a7a1-26a3478d3d27.png)
