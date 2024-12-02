# kotlin-blackjack

- [x] 카드는 에이스(1,11)부터 2~10 JACK(10) QUEEN(10) KING(10)이 있다
- [x] 플레이어는 임의의 카드를 두장 지급 받는다 플레이어간의 카드는 중복되지 않는다
- [x] 플레이어는 가진 카드의 합이 21이 넘지 않으면 추가 카드를 뽑을 수 있다
- [x] 플레이어 중 카드의 수의 합이 21에 가까운 플레이어가 승리한다

# blackjack domain

- [x] 카드
  - [x] 모양을 가진다 (Diamond, Heart, Spade, Clover)
  - [x] 숫자를 가진다 (Ace, 2~10, Jack, Queen, King)
    - [x] 숫자는 여러 값을 가질 수 있다 ex Ace(1, 11)
- [x] 덱
  - [x] 카드들을 가진다
  - [x] 플레이어들은 덱에서 카드를 뽑는다
  - [x] 뽑은 카드들은 서로 중복되지 않는다
- [x] 플레이어
  - [x] 카드들을 가진다
  - [x] 최초에 두장의 카드를 덱에서 뽑는다
  - [x] 두장의 값의 합이 21을 넘지 않으면 추가 카드를 뽑을 수 있다
- [x] 게임
  - [x] 플레이어의 리스트를 가진다
  - [x] 플레이어 중 카드의 합이 21이 넘지 않으며 21에 가까운 플레이어가 승리한다