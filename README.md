# kotlin-blackjack

## 🚀 2단계 - 블랙잭

### 기능 요구사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 프로그래밍 요구사항
- 모든 엔티티를 작게 유지한다.

### TODO
- [x] **[페어로 진행]** 카드는 문자와 문양으로 만들 수 있다.
- [x] **[페어로 진행]** 카드는 A, 2-9, J, Q, K 로 구성된다.
- [x] **[페어로 진행]** 카드는 스페이드, 클로버, 하트, 다이아몬드 문양으로 구성된다.
- [x] **[페어로 진행]** 카드의 총 개수는 52장이다.
- [x] 중복없는 52장의 카드 세트는 Deck이다.
- [x] Deck을 생성했을 때는 카드가 섞여있다.
- [x] Deck에서는 랜덤으로 카드를 한 장 뽑을 수 있다.
  - 뽑은 카드는 Deck에서 제거한다.
  - Deck에서 가장 마지막 장을 뽑는다.
- [x] Deck에서 더 이상 카드가 없으면 예외를 발생한다.
- [x] 플레이어는 이름을 갖는다.
- [x] 플레이어의 이름은 10자를 넘을 수 없다.
- [x] 플레이어는 덱에서 카드를 뽑을 수 있다.
  - 뽑은 카드는 플레이어가 갖고 있는다. (리스트 형태로)
- [x] 카드 점수 계산 규칙
  - [x] 카드 리스트를 받으면, 모두 더해서 점수를 계산해서 반환한다. (sumAll)
  - [x] King, Queen, Jack 은 10으로 계산한다.
  - [x] Ace는 1 또는 11로 계산한다.
    - Ace 카드 받은 후 먼저 11로 계산했을 때, 21을 초과하면 1로 계산한다.
- [x] 플레이어는 뽑은 카드의 총 점수를 알고 있다.
- [x] 플레이어가 소지한 카드의 합이 21을 초과하면 카드를 더이상 받을 수 없다.
- [x] 게임이 시작하면 각 플레이어는 두 장의 카드를 받는다.
- [x] 플레이어는 카드를 더 받을 수 있다.
- [x] 모든 플레이어가 카드를 더 받지 않는다고 결정하면 게임이 종료된다.
  - 총 점수가 21이 넘으면 더이상 카드를 받지 못하고, 이는 더 받지 않는 걸로 판단한다.
- [x] 게임이 종료되면 받은 카드와 게임 결과를 출력한다.


## 🚀 3단계 - 블랙잭(딜러)
### 추가 기능 요구사항
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### 추가 프로그래밍 요구사항
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

### TODO
- [x] 딜러를 추가한다.
  - 참가자(Participant)는 Player를 상속하고, 게임 참가자 역할을 한다.
  - Player를 상위 객체로 하고,
    - `draw()`, `canDraw()`를 추상 메서드로 선언하고, 하위 객채에서 구현
  - Dealer는 Player를 상속하고, 딜러 역할을 한다.
- [x] 딜러는 참가자와 비교해서 승패를 결정할 수 있다.
  - 결과: 승, 패, 무
- [x] 딜러와 참가자들의 게임 결과들을 저장하는 GameResults
- [x] 딜러는 처음에 플레이어와 똑같이 2장을 받는다.
- [x] 플레이어가 카드를 모두 받은 후에는, 딜러가 처음에 받은 카드 2장의 합계가 16이하면 반드시 1장의 카드를 추가로 받아야한다.
- [x] 딜러가 추가로 받은 후(받을 수 있는 경우에), 경기는 종료되고 승패가 결정된다.