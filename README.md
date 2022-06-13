# kotlin-blackjack

## 코틀린 DSL

### 구현 예시

```text
introduce {
  name("이요한")
  company("카카오")
  skills {
    soft("A passion for problem solving")
    soft("Good communication skills")
    hard("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
```

### 구현 목록

- 최상위 함수
  - [X] introduce 함수 구현
  
- `이력서(Resume)`
  - 이름, 회사, 스킬 목록, 언어 목록을 상태로 가진다

- `이력서 빌더(ResumeBuilder)`
  - [X] name 함수 구현
  - [X] company 함수 구현
  - [X] skills 함수 구현
  - [X] languages 함수 구현

- `스킬(Skill)`
  - 스킬 타입과 이름을 상태로 가진다

- `스킬 빌더(SkillBuilder)`
  - [X] soft 함수 구현
  - [X] hard 함수 구현 

- `언어(Language)`
  - 이름과 단계를 상태로 가진다

- `언어 빌더(LanguageBuilder)`
  - [X] 중위 표기 구현

## 블랙잭

### 기능 요구사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
  - 단, 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

### 실행 결과
```text
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi, jason에게 2장의 나누었습니다.
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```

### 프로그래밍 요구 사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

### 도메인 모델

- `블랙잭 결과(BlackJackResult)`
  - [X] 각 플레이어별로 승패를 확인할 수 있다

- `딜러(Dealer)`
  - [X] 딜러는 카드 합계가 17 이상이면 플레이를 종료한다 
  - [X] 딜러는 플레이어에게 카드를 나누어줄 수 있다.
  - [X] 딜러는 카드를 추가할 수 있다
    - 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
  - [ ] 딜러는 참가자와의 배팅 결과를 알 수 있다.
    - 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

- `블랙잭(BlackJack)`
  - [X] 게임이 시작되면 각 참가자와 딜러가 2장의 카드를 나누어준다.
  - [X] 카드를 추가할 수 있는 참가자에게 카드를 지급한다
  - [X] 게임이 종료되었는 지 확인할 수 있다
    - [X] 모든 참가자와 딜러가 카드를 추가할 수 없으면 게임이 종료된다 
  - [X] 결과를 확인할 수 있다
    - [X] 게임이 종료되지 않았으면 IllegalStateException 이 발생한다

- `게임 참가자들(Players)`
  - [X] 각 플레이어에게 카드를 나누어준다.
  - [X] 카드를 추가할 수 있는 참가자를 조회한다
  - [X] 모든 참가자가 종료되었는 지 확인할 수 있다
  - [ ] 참가자들의 배팅 결과를 알 수 있다.

- `게임 참가자(Player)`
  - [X] 참가자 상태를 변경할 수 있다.
    - STAY 또는 BUST 인 경우 IllegalStateException 이 발생한다.
  - [X] 카드를 추가할 수 있다.
    - STAY 또는 BUST 인 경우 IllegalStateException 이 발생한다.
  - [X] 카드들의 점수의 합을 구할 수 있다.
  - [X] 참가자의 게임 참여가 종료되었는 지 확인할 수 있다.
    - STAY 또는 BUST 가 아니면 참여가 종료되지 않는다.

- `덱(Deck)` 
  - [X] 카드를 뽑을 수 있다.

- `카드들(Cards)`
  - [X] 카드를 추가할 수 있다. 
  - [X] 카드들의 점수의 합을 구할 수 있다.

- `카드(Card)`
  - 카드 문양과 카드 타입을 상태로 가진다.
  - [X] 카드가 동일한지 비교할 수 있다

- `카드 문양(Suit)`
  - 네 가지 문양을 가진다. (다이아몬드, 스페이드, 클로버, 하트) 

- `카드 유형(Denomination)`
  - 카드 이름과 점수를 가진다.
  - [X] 카드 유형이 동일한지 비교할 수 있다
  - 점수를 반환한다
    - [X] 카드 숫자는 숫자 그대로 반환한다
    - [X] Ace는 1 또는 11로 반환한다
    - [X] King, Queen, Jack은 각각 10으로 반환한다

- `점수(Score)`
  - [X] 점수를 더할 수 있다
  - [X] 점수를 비교할 수 있다
