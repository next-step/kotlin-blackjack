# kotlin-blackjack

## STEP 3

## 피드백 - 1

- [x] Player가 한 장의 카드도 받을 수 있도록 변경
- [ ] Dealer와 Player의 중복 코드를 조합으로 풀어내기
- [x] 패키지 구조 정리 ex) GameResult
- [ ] BlackJackGame의 프로퍼티를 Dealer, Players에서 Participants로 변경

## 기능 요구 사항

- [x] 딜러가 블랙잭 게임에 추가된다.
- [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다
- [x] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다
- [x] 게임을 완료한 후 각 플레이어별로 승패를 출력한다
- [ ] 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.
- [ ] 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.

## 기능 구현 사항

- [x] 딜러가 블랙잭 참여자로 추가된다.
  - [x] 딜러는 블랙잭 게임과 협력한다.
- [x] 딜러도 두 개의 카드를 받지만, 첫 번째 카드만 오픈한다.
  - [x] 두 개의 카드를 가지고 있지만, 첫 번째 받은 카드만 출력한다.
- [x] 버스터가 되지 않은 상태에서 플레이어가 더 이상 카드를 받지 않을 때 승패가 결정된다.
  - [x] 게임 결과는 Result를 통해 출력한다.
    - [x] Result에는 Player, Dealer에 대한 승패 정보가 담긴다.
      - [x] Player와 Dealer를 받을 수 있는 상위 객체가 있어도 될 듯?
      - [x] PlayerName을 상속 받을 수 있는 -> 추후에 take도 공통화 될 수 있을듯
- [x] 게임 결과는 딜러 -> 플레이어 순으로 출력한다.
- [x] 플레이 결과는 딜러와 플레이어를 가진다. (Result(Dealer, List<Player>))
  - [x] 플레이어의 턴이 끝이 끝나면 딜러의 턴이 시작된다.
    - [x] 딜러의 카드 합계가 16이하이면 카드를 더 받는다.
  - [x] 플레이어와 딜러의 턴이 끝나면 Result를 반환한다.
- [x] 플레이어와 딜러의 턴을 관리하는 TurnMachine 추가
- [x] 승패의 결과는 최종에서 결정된다.
  - [x] 버스터된 플레이어는 패배하고, 딜러는 승리한다.
  - [x] 딜러와 플레이어 중 21에 가까운 사람이 승리한다.
  - [x] 딜러 19, 플레이어 22 -> 테스트 케이스 확인 필요
  - [ ] 딜러가 버스터 상태가 된다면 -> 플레이어들이 전부 승리?
  - [x] ACE 2가지 경우에 수에 테스트가 되어야 한다.
    - [x] ACE, 10, 10 -> 21
    - [x] ACE, 10 -> 21
- [x] Participant을 리팩터링한다.
  - [x] open 제거
  - [x] take, score 제거
    - 모든 참여자가 카드를 받아야 않도록 하는 정책 변경이 될 수도 있기 때문
- [x] 플레이어는 상태를 가진다.
  - [x] 버스트
  - [x] 블랙잭
  - [x] 스탠드
  - [x] 히트

### Improvement Point

- [x] 1. inputMoreCard를 외부에서 주입하도록 한다. (추가 카드를 받는 기능을 제어 가능하도록 한다)
- [x] 2. BlackJackGame에 아래 코드를 이동시킨다.
   - 외부에서 어떻게 메시지를 주고 받을지 고민해야 한다.
- [x] 3. 최종 결과로 Result를 던지도록 한다.
  - Result에는 Player, Dealer에 대한 정보가 담긴다


## STEP 2

### 피드백 - 2

- [ ] 플레이어의 상태 코드를 관리해보자
  - [x] 블랙잭 도메인 규칙을 정리

### 피드백 - 1

- [x] 정적 팩터리 메서드로 Players.of(playerNames)로 변경
- [x] CardNumber의 이름을 개선
- [x] ONE -> ACE
- [x] 플레이어가 추가 카드를 받는 것에 대한 로직을 개선
- [x] 블랙잭 규칙을 다시 알아보기
  - [x] Cards.bestScore() 변경 필요
- [ ] 플레이어의 상태(Bust, Blackjack)를 추가
- [x] 버스터에 대한 테스트 케이스 추가
  - [x] 에이스없이 총합이 21을 초과하는 경우(10,10,2) - 버스터
  - [x] 에이스없이 총합이 21을 넘지않는 경우 - 버스터가 아님
  - [x] 에이스가 있고 총합이 21을 초과하지 않는 경우(10,10,1) - 버스터가 아님
  - [x] 에이스가 있고 총합이 21을 초과하는 경우(10,10,1,1) - 버스터

### 도메인 분석

- 블랙잭 게임은 딜러에게 카드를 받아 21에 가까운 수를 만드는 사람이 승리한다.
  - 21을 넘으면 패배한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받는다.
- 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 승리한다.
- 21을 넘지 않을 경우 원한다면 언제든지 카드를 계속 뽑을 수 있다.
- 대결 구도는 딜러 vs 플레이어

### 개념 모델

- 카드
  - 숫자 (2 ~ 10)
  - 무늬 (하트, 다이아몬드, 클럽, 스페이드)
  - Ace (1 또는 11) 
  - King, Queen, Jack
- 카드 덱
  - 블랙잭에서 덱은 플레이에 사용되는 카드 한 벌(52장의 카드로 구성된 카드 세트)을 뜻한다.
- 용어
  - 스플릿: ??
  - 버스트: 21을 초과하는 상황
    - 블랙잭에서 21을 초과하면 패배\
    - ex) 10과 9를 가지고 있을 때 3을 더하면 22가 되어 버스트가 된다.
- 플레이어

### 기능 요구 사항

- [ ] 숫자 계산은 카드 숫자를 기본으로 한다.
  - [ ] 숫자는 2 ~ 10까지의 숫자를 가진다.  
  - [ ] King, Queen, Jack 카드는 10으로 계산한다.
  - [ ] Ace 카드는 1 또는 11로 계산한다.
- [ ] 모든 엔티티를 작게 유지한다.

### 기능 구현 사항

- [x] 카드를 생성한다.
  - [x] 카드는 1~13까지의 숫자와 무늬(하트, 다이아몬드, 클럽, 스페이드)의 조합으로 이루어진다. 
  - [x] 총 52(13*4)개의 카드가 미리 생성돼있어야 한다.
- [ ] 플레이어가 존재한다.
  - [ ] 플레이어는 여러 명이 존재할 수 있다.
  - [ ] 플레이어는 카드를 여러 장 가질 수 있다.
