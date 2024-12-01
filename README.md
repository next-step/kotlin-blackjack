# kotlin-blackjack
## 🚀 2단계 - 블랙잭
### 기능 요구사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 구현할 기능 정의
#### 카드 관련 기능
- [x] 카드 생성
    - [x] 52장의 카드를 생성한다.
    - [x] 카드의 모양(Suit)을 정의한다.
    - [x] 카드의 숫자와 계급(Rank)을 정의한다.

- [x] 카드 점수 계산
    - [x] ACE의 점수를 1 또는 11로 계산할 수 있다.
    - [x] 숫자 카드의 점수를 고정 값으로 계산한다.
    - [x] 그림 카드(J, Q, K)의 점수를 고정 값(10)으로 계산한다.

- [x] 카드 덱 관리
    - [x] 카드를 섞는다.
    - [x] 덱에서 카드를 한 장씩 나눈다.
    - [x] 덱이 비었을 때 카드 나누기를 방지한다.

#### 플레이어 관련 기능
- [x] 카드 분배
    - [x] 게임 시작 시 플레이어와 딜러에게 카드를 두 장씩 나눈다.

- [x] 플레이어 점수 계산
    - [x] 플레이어가 가진 카드의 점수를 계산한다.
    - [x] ACE를 포함한 경우 최적의 점수를 선택한다.

- [x] 카드 추가 요청
    - [x] 플레이어가 추가 카드를 받을지 선택할 수 있다.
#### 게임 진행 기능
- [x] 게임 초기화
    - [x] 플레이어 이름을 입력받는다.
    - [x] 입력받은 이름으로 플레이어 객체를 생성한다.
    - [x] 초기 카드를 플레이어에게 분배한다.

- [x] 게임 진행
    - [x] 플레이어의 추가 카드 요청 여부를 입력받는다.
    - [x] 입력에 따라 카드를 추가로 분배한다.

- [x] 게임 종료
    - [x] 최종 점수를 계산한다.
    - [x] 결과를 출력한다.

## 🚀 3단계 - 블랙잭(딜러)
딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

### 기능 요구사항
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### 구현할 기능 정의
- [x] 딜러와 플레이어를 참가자 공통분모로 묶기 
- [x] 딜러 초기 카드 분배
  - [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가
  - [x] 딜러는 17점 이상이면 추가로 받을 수 없다.
- [x] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
- [x] 승패 판단
