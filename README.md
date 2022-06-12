# kotlin-blackjack

## step1
- [x] kotlin DSL 구현

## step2
- [ ] Blackjack Domain을 구현한다.
  - [x] Card를 구현한다. 
    - [x] Card는 Suit(문양)과 Denomination(끗수)를 가진다.
    - [x] Card는 String으로 변환할 수 있다. e.g. ♠A
  - [x] Deck을 구현한다.
    - [x] Deck는 52장의 서로 다른 문양과 끗수로 이루어진다.
    - [x] Deck는 섞을 수 있다.
    - [x] Deck에서 카드를 한 장 또는 여러장 뽑을 수 있다.
  - [x] Player를 구현한다.
    - [x] Player는 이름을 가진다.
    - [x] Player는 여러장의 카드를 가질 수 있다.
    - [x] Player에 카드를 추가할 수 있다.
  - [x] BlackJack 구현
    - [x] Deck과 Player들을 가진다.
    - [x] Player는 Deck에서 카드를 뽑을 수 있다.
    - [x] Ace는 1 or 11로 계산할 수 있다.
    - [x] Jack, Queen, King은 10으로 계산한다.
- [x] Blackjack Game Controller를 구현한다.
  - [x] 게임 참여자를 입력받는다.
  - [x] 플레이어는 두 장의 카드를 지급 받는다.
  - [x] 카드를 추가로 받을 수 있다.
    - [x] 카드의 합이 21 이상이면 카드를 받을 수 없다.
  - [x] 결과를 계산할 수 있다.
  - [x] 결과를 출력한다.
