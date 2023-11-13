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


## STEP2 (2단계 - 블랙잭)
### 기능 요구사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 기능 목록
- [ ] 블랙잭 게임 참가자를 입력 받는다.
  - 빈 문자열이 들어올 경우 예외를 던진다.
  - 공백, 개행, 탭이동 문자만 들어올 경우 예외를 던진다.
  - 문자열 "pobi,,jain"이 들어올 경우 예외를 던진다.
  - 문자열 "pobi,jason,jain"이 들어올 경우 참가자 [pobi, jason, jain]가 등록되어야 한다.
- [ ] 블랙잭 게임 참가자는 카드를 가지고 있을 수 있다.
  - 참가자 [pobi, jason]에게 각각 [2하트, 8스페이드]와 [7클로버, K스페이드]를 나누어 줄 경우 pobi = [2하트, 8스페이드], jason = [7클로버, K스페이드]를 가지고 있어야 한다.
- [ ] 블랙잭 게임 참가자는 카드를 더 받고 싶은 경우 y, 아닌 경우 n을 입력해야한다.
  - 참가자 [pobi]가 [King하트, 4스페이드] 가지고 있는 상태에서 "y"를 입력 할 경우 카드가 한장 더 늘어나야 한다.
  - 참가자 [pobi]가 [King하트, 4스페이드] 가지고 있는 상태에서 "n"를 입력 할 경우 카드가 유지되어야 한다.
- [ ] 블랙잭 게임 참가자가 가지고 있는 카드의 합이 21을 넘지 않을 경우 카드를 더 받을 수 있다.
  - 참가자 [pobi]가 [King하트, 4스페이드]를 가지고 있을 경우 카드를 더 받을 수 있다.
  - 참가자 [pobi]가 [King하트, 4스페이드, 6클로버]를 가지고 있을 경우 카드를 더 받을 수 있다.
  - 참가자 [pobi]가 [King하트, ace스페이드, 6클로버]를 가지고 있을 경우 카드를 더 받을 수 있다.
  - 참가자 [pobi]가 [King하트, ace스페이드, 6클로버, ace하트]를 가지고 있을 경우 카드를 더 받을 수 있다.
  - 참가자 [pobi]가 [King하트, 4스페이드, 7클로버]를 가지고 있을 경우 카드를 더 받을 수 없다.
  - 참가자 [pobi]가 [King하트, 4스페이드, 8클로버]를 가지고 있을 경우 카드를 더 받을 수 없다.

### 프로그래밍 요구 사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.