# kotlin-blackjack

---

# 블랙잭

## 요구 사항 분석

- 입력
  - [x] 게임에 참여할 사람을 입력받는다.
  - [x] 한장의 카드를 더 받을지 말지 입력받는다.
  - [] 플레이어의 배팅 금액을 입력받는다.

- 출력
  - [x] 플레이어가 가지고 있는 카드를 출력한다.
  - [x] 게임의 결과를 출력한다.
    - [x] 각 플레이어 별로 승패를 출력한다.

- 도메인
  - [x] 카드가 있다.
    - [x] 카드는 숫자와 무늬를 가진다.
    - [x] Ace, King, Queen, Jack 이라는 카드도 있다.
  - [x] 카드 덱이 있다.
    - [x] 카드 덱은 총 51장의 카드를 가진다.
  - [x] 플레이어가 있다.
    - [x] 플레이어는 이름을 가진다.
    - [x] 플레이어는 카드를 가질 수 있다.
  - [x] 블랙잭 게임이 있다.
    - [x] 게임 시작시 두 장의 카드를 지급받는다.
      - [] 처음 두 장의 카드 합이 21일 경우 베팅 금액의 1.5배를 딜러에게 받는다.
      - [] 딜러와 플레이어 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
    - [x] 플레이어는 가지고 있는 카드가 21을 넘지 않은 경우 카드를 계속 뽑을 수 있다.
      - [] 카드를 추가로 뽑아 21을 초과하는 경우 배팅 금액을 모두 잃는다.
    - [x] 딜러는 플레이어들이 전부 카드를 뽑은 뒤에 카드를 뽑는다.
      - [x] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 패에 상관없이 승리한다.
      - [] 남아 있는 플레이어들은 베팅 금액을 받는다.
  - [x] 블랙잭 점수 계산기가 있다.
    - [x] Ace는 1 또는 11로 계산할 수 있다.
    - [x] King, Queen, Jack 는 10으로 계산한다
    - [x] 플레이어의 카드를 점수로 계산해 결과를 내보낸다
  - [x] 딜러가 있다.
    - [x] 딜러는 처음에 받은 2장의 합계가 16이하라면 반드시 한장의 카드를 받는다.
      - [x] 16점 이하면 계속 카드를 뽑는다.
    - [x] 17점 이상이면 카드를 추가로 받을 수 없다.

## 용어 정리

- 버스트(Bust)
  - 카드 총합이 21을 넘은 경우
    - 플레이어가 버스트 당하면 패배가 확정된다.
    - 딜러가 버스트 당하면 그 시점까지 남아있는 모든 플레이어가 승리한다.
- 블랙잭(Blackjack)
  - A한장과 10, J, Q, L 로 21을 이루는 경우 베팅 금액의 1.5배를 돌려준다.