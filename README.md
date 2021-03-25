# kotlin-blackjack

- [X] 카드가 있다.
    - [X] 문양이 있다. => CardSuite
      - [X] 다이아몬드, 하트, 스페이드, 클로버가 있다.
    - [X] 숫자가 있다. => CardNumber
        - [X] King, Queen, Jack 은 10을 의미한다.
        - [X] 숫자는 2~10까지 있다.
        - [X] Ace는 1 또는 11이다.
    
- [ ] 블랙잭은 게임 룰을 가진다.
    - [ ] 처음 받은 2개의 두 카드의 숫자 합이 21이면 Blackjack 이다.
    - [ ] 카드의 숫자의 합이 21을 넘으면 Bust 이다.
    - [ ] 카드의 숫자의 합이 21미만이면 Hit 이다.
        - [ ] 카드의 숫자의 합이 21미만이면 카드를 받을 수 있다.
        - [ ] 카드의 숫자의 합이 21미만이면 카드를 더 이상 받지 않을 수 있다.
