# kotlin-blackjack

## 블랙잭 규칙

블랙잭은 카지노 게임의 한 종류로, 플레이어와 딜러가 참여하는 게임이다. 각 참가자는 카드의 합이 21 또는 21에 가장 가까운 수가 되도록 카드를 뽑아야 한다.
플레이어의 점수가 21을 초과하면 패배한다 ("bust").

블랙잭은 52장의 표준 카드 덱을 사용한다. 각 카드는 에이스(A), 2, 3, 4, 5, 6, 7, 8, 9, 10, 잭(J), 퀸(Q), 또는 킹(K)의 랭크을 가진다.
카드의 랭크가 카드의 점수를 결정한다. 숫자 카드 2에서 10은 해당 숫자만큼의 가치가 있고, 페이스 카드(잭, 퀸, 킹)는 10점으로 계산된다.
에이스는 1점 또는 11점으로 계산되며, 손의 합이 21에 더 가깝지만 넘지 않도록 하는 값을 선택한다.

### 딜링

각 게임은 카드 딜링부터 시작해서 다음과 같이 진행된다:

- 딜러는 덱을 셔플하고 카드들을 딜링한다:
  - 카드는 라운드로빈 방식으로 각 플레이어에게 한 장씩 (딜러의 왼쪽에서 시작하여 시계 방향으로) 배분된다.
  - 딜러에게는 한 장의 카드를 앞면이 보이도록 배분한다 (랭크가 보인다).

이 시점에서 모든 플레이어와 딜러는 한 장의 카드를 갖게 된다.

- 다시 각 플레이어에게 한 장의 카드를 딜링하고
- 딜러에게는 이번에는 뒷면이 보이도록 한 장의 카드를 배분한다 (그래서 플레이어들은 카드의 랭크를 볼 수 없다).

이제 모두 두 장의 카드를 갖게 된다.

블랙잭을 (에이스와 10점짜리 카드) 가진 플레이어는 즉시 승리합니다 (딜러도 블랙잭이 아닌 경우) 그리고 게임의 나머지 턴을 진행하지 않습니다.

### 플레이러 턴

- 첫 번째 플레이어부터 게임을 시작합니다:

  - 딜러는 첫 번째 플레이어에게 "히트" (덱에서 추가 카드를 받는 것) 또는 "스탠드" (더 이상 카드를 받지 않는 것)를 원하는지 묻는다.
  - 만약 "스탠드"를 선택하면, 플레이어의 턴은 끝나고 게임 액션은 다음 플레이어로 넘어간다.
  - 플레이어가 "히트"를 선택하면, 딜러는 덱에서 한 장의 카드를 앞면이 보이도록 추가 지급하고, 추가된 카드에 따라 핸드 점수가 변경된다.
  - 이로 인해 점수가 21을 초과하면, "버스트" 하여 즉시 패배하고, 게임은 다음 플레이어로 계속된다.
  - 버스트되지 않았다면, 다시 "히트"나 "스탠드"를 선택할 수 있는 기회를 갖는다.

### 딜러 턴

모든 플레이어들의 턴 종료 후, 딜러의 차례가 된다. 만역 모든 플레이어가 이미 버스트했다면 게임은 즉시 종료된다.

- 우선 딜러는 앞면이 보이지 않던 카드를 뒤집어 공개한다.
- 그런 다음, 딜러는 점수가 17 이상이 될 때까지 카드를 추가로 받으며, 그 시점에는 반드시 스탠드해야 한다.
- 딜러는 히트나 스탠드를 선택할 수 없으며, 핸드의 점수가 16 이하이면 반드시 히트해야 하고, 17 이상이면 반드시 스탠드해야 한다.

### 승부

딜러가 버스트하면, 버스트하지 않은 모든 플레이어는 자동으로 승리한다. 그렇지 않은 경우, 각 플레이어는 딜러보다 높은 점수를 가지면 승리하고, 낮은 점수를 가지면 패배하며,
점수가 같으면 "무승부" 또는 "푸시"가 됩니다.

## 3단계 - 블랙잭(딜러)

### 피드백

- [x] `Controller` 적절한 패키지에 배치
- [x] `Rank` 와 `Suit` 에 있는 display 속성 제거
- [x] `BlackjackController.handle()` 함수에서 뷰 로직을 분리
- [x] 도메인 객체들의 불필요한 `toString()` 오버라이드 제거
- [?] 플레이어의 턴에서 21이 되면 더 이상 해당 플레이어에게 카드를 받지 않고 다음 턴으로 넘어간다. (경험상 카지노에서 플레이어 실수를 묵인하기에 이부분은 선택사항이라고 생각한다.)

### 기능 요구사항

- [x] 카드의 합을 구할 수 있다.
- [x] Ace 가 포함된 경우 1점 또는 11점으로 21에 더 가까운 값을 선택한다.
- [x] 카드는 앞면이 보이는지 여부를 속성으로 가진다.
- [x] 카드를 뒤집을 수 있다.
- 딜러 객체를 구현한다.
  - [x] 딜러는 두 번째 카드를 뒤집어 받는다.
  - [x] 딜러는 (딜러 턴에) 두 변쪠 카드를 뒤집을 수 있다.
  - [x] 딜러는 점수가 16이하인 경우, 덱에서 카드를 뽑을 수 있다.
- 초기화
  - [x] 게임에 참여할 사람들의 이름을 입력받는다.
  - [x] 플레이어들에게 두 장의 카드를 지급한다.
  - [x] 딜러에게 두 장의 카드를 지급한다.
    - [x] 딜러의 첫번째 카드는 앞면이 보인다.
    - [x] 딜러의 두번째 카드는 뒷면이 보인다.
  - [x] 두 장의 카드의 합이 21인 경우 블랙잭이다.
  - [x] 딜러가 블랙잭인 경우, 플레이어 턴을 자동 종료한다.
  - 초기 상태를 출력한다.
    - [x] 플레이어들의 초기 상태를 화면에 출력한다.
    - [x] 딜러의 첫번쩨 카드는 출력되지 않는다.
- 게임 운영
  - 플레이어들의 턴을 진행한다.
    - [x] 카드를 더 받을지 여부를 입력받는다.
    - [x] 차례가 종료 될 때까지 그 플레이어의 차례가 반복된다.
    - [x] 21을 초과하지 않고 사용자의 입력이 'y' 인 경우, 경우 카드를 계속 뽑을 수 있다.
    - [x] 21을 초과하거나 사용자의 입력이 'n' 인 경우, 카드를 더 받지 않을 수 있다.
  - 딜러의 턴을 진행한다.
    - [x] 플레이어들의 턴 종료 후, 딜러의 턴이 시작한다.
    - [x] 뒷면만 보이던 딜러의 두번째 카드를 뒤집어 공개한다.
    - 딜러는 카드 뽑는 로직을 수행한다.
      - [x] 딜러는 핸드의 점수가 16 이하이면 반드시 히트해야 하고, 17 이상이면 반드시 스탠드해야 한다.
      - [x] 결과가 아직 정해지지 않은 경우에만 (즉, 스탠드인 플레이어가 있는 경우), 딜러는 행동을 취한다.
  - [x] 모든 플레이어들이 턴 종료 시 게임이 종료된다.
- [x] 딜러의 행동을 출력한다.
- [x] 각 플레이어의 카드들을 회면에 출력할 수 있다.
- [x] 게임이 종료된 후 각 플레이어들의 점수를 출력한다.
- 최종 승패
  - [x] 플레이어가 버스트인 경우 패배한다.
  - [x] 딜러가 버스트한 경우 버스트하지 않은 플레이어는 승리한다.
  - [x] 딜러와 플레이어가 모두 버스트한 경우 플레이어는 패배한다.
  - [x] 플레이어가 블랙잭이고 딜러가 블랙잭이 아닌 경우 승리한다.
  - [x] 플레이러와 딜러 모두 블랙잭이면 무승부다.
  - [x] 플레이어의 점수가 딜러의 점수보다 높으면 승리한다.
  - [x] 플레이어 점수와 딜러의 점수가 같은 경우 무승부다.
  - [x] 플레이어의 점수가 딜러의 점수보다 낮으면 패배한다.
  - [x] 최종 승패를 출력한다.
- 베팅
  - [x] 플레이어의 베팅 금액을 입력받는다.
  - [x] 플레이어는 베팅을 걸 수 있다.
  - 최종 수익을 계산한다.
    - [ ] 플레이어가 블랙잭이고 딜러가 블랙잭이 아닌 경우 베팅 금액의 1.5배를 딜러에게서 받는다.
    - [x] 플레이어가 승리한 경우 (블랙잭 x) 베팅 금액을 딜러에게서 받는다.
    - [x] 플레이어가 패배한 경우 베팅 금액을 딜러에게 잃느다.
    - [x] 무승부인 경우 베팅 금액을 돌려받는다.
  - [ ] 딜러와 플레이어들의 최종 수익을 출력한다.

게임 예시:

```text
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장의 나누었습니다.
딜러: 3다이아몬드
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

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```
