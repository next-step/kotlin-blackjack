# kotlin-blackjack

## STEP1 (1단계 - 코틀린 DSL)
### 실습 환경 구축
[블랙잭 저장소](https://github.com/next-step/kotlin-blackjack)를 기반으로 미션을 진행한다. [온라인 코드 리뷰 요청 1단계 문서](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step1.md)를 참고해 실습 환경을 구축한다.
1. 미션 시작 버튼을 눌러 미션을 시작한다.
2. 저장소에 자신의 GitHub 아이디로 된 브랜치가 생성되었는지 확인한다.
3. 저장소를 자신의 계정으로 Fork 한다.


- 코드리뷰 요청 1단계 [[동영상]](https://www.youtube.com/watch?v=YkgBUt7zG5k) [[문서]](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step1.md)
- 코드리뷰 요청 2단계 [[동영상]](https://www.youtube.com/watch?v=HnTdFJd0PtU) [[문서]](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step2.md)
- 코드리뷰 요청 3단계 [[동영상]](https://www.youtube.com/watch?v=fzrT3eoecUw) [[문서]](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step3.md)

### 좋은 개발 코드의 8가지 특징
- 잘 작동한다.
- 읽기 쉽다.
- 테스트 가능하다.
- 관리가 쉽다.
- 외관이 보기 좋다.
- 변경이 쉽다.
- 간결하다.
- 효율적이다.

### API가 깔끔하다
- 읽기 쉽다.
- 외관이 보기 좋다.
- 간결하다.

### 코틀린은 간결한 구문을 어떻게 지원하는가?
- 확장 함수
- 중위 호출
- 연산자 오버로딩 
- get 메서드에 대한 관례 
- 람다를 괄호 밖으로 빼내는 관례
- 수신 객체 지정 람다

### 도메인 특화 언어
DSL(Domain-specific language) ↔ 범용 프로그래밍 언어

- 선언적 언어
- 세부 실행은 언어를 해석하는 엔진에 맡긴다.
- 컴파일 시점에 제대로 검증하는 것이 어렵다.
- ex) e.g. SQL, 정규 표현식

### 코틀린 DSL
- 범용 언어(= 코틀린)로 작성된 프로그램의 일부
- 범용 언어와 동일한 문법을 사용한다.
- 호출 결과를 객체로 변환하기 위해 노력할 필요가 없다.
- 타입 안전성을 보장한다.
- 코틀린 코드를 원하는 대로 사용할 수 있다.
