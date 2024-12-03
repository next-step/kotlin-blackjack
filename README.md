# kotlin-blackjack

## 🚀 2단계 - 블랙잭

### 기능 요구 사항

- 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
    - 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
    - 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수
      있다.

### 기능 구현 목록

- 카드 (Card)
    - [x] 랭크(Rank)와 수트(Suit)를 가진다.
        - [x] 랭크는 Ace, 2~10, King, Queen, Jack 중 하나이다.
            - [x] Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
        - [x] 수트는 하트, 클로버, 다이아몬드, 스페이드 중 하나이다.
    - [x] 카드가 에이스인지 여부를 반환할 수 있다.


- 덱 (Deck)
    - [x] 카드를 가진다.
        - [x] 카드를 한 장 뽑을 수 있다.


- 핸드 (Hand)
    - [x] 카드를 가진다.
        - [x] 카드를 추가할 수 있다.
            - [x] 가지고 있는 카드의 합이 21을 초과하지 않을 때만 가능하다.
    - [x] 카드의 합을 계산할 수 있다.
        - [x] Ace는 1 또는 11로 계산할 수 있다.
        - [x] King, Queen, Jack은 각각 10으로 계산한다.
        - [x] 카드의 합이 21을 초과하면 0을 반환한다.
    - [x] 핸드에 가지고 있는 카드들을 반환할 수 있다.

- 플레이어 (Player)
    - [x] 이름을 가진다.
    - [x] 핸드(Hand)를 가진다.
        - [x] 핸드는 두 장의 카드로 시작한다.
    - [x] 카드를 추가로 뽑을 수 있는 상황인지 확인할 수 있다.
    - [x] 자신이 가진 카드의 합을 반환할 수 있다.
        - [x] 에이스가 있는 경우 최선의 합을 반환한다.

### 🚀 3단계 - 블랙잭(딜러)

### 기능 요구 사항

- 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
    - 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
    - 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수
      있다.
    - 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
    - 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
    - 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### 기능 구현 목록

- 딜러 (Dealer)
    - [ ] 기본 구성은 플레이어와 같다.
    - [ ] 카드를 추가로 뽑을 수 있는 상황인지 확인할 수 있다.
        - [ ] 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가한다.
        - [ ] 카드의 총 점수가 17점 이상이면 추가로 받을 수 없다.


- 게임 결과 (GameResult)
    - [ ] 각 플레이어와 딜러의 승패를 판단할 수 있다.
