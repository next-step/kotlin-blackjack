# kotlin-blackjack

[게임 규칙](https://namu.wiki/w/%EB%B8%94%EB%9E%99%EC%9E%AD(%EC%B9%B4%EB%93%9C%EA%B2%8C%EC%9E%84)#s-4.1)

## 2단계

- [X] 블랙잭을 위한 덱을 구성한다
    - [X] 플레잉카드를 만든다 [참고](https://ko.wikipedia.org/wiki/%ED%94%8C%EB%A0%88%EC%9E%89_%EC%B9%B4%EB%93%9C)
        - [X] 플레잉카드는 4가지 중 하나의 슈트(suit)와, 끗수(denomination)를 가지고 있다.

- [X] 플레이어의 진행 상황을 나타내는 객체를 생성한다.
    - [X] 모든 상태는 card들을 가지고 있으며, 현재 점수와 추가적으로 뽑을지 말지 여부를 결정할 수 있다.
    - [X] START(아무것도 없는 상태)
        - draw시 2장을 뽑는다
        - 점수에 따라 Blackjack,Hit로 변경 가능
        - 카드 뽑기 종료 불가
    - [X] Blackjack(2장이며 포인트가 21점인 경우)
        - ACE 한장과, J,Q,K 중 한장으로 구성될 수 있다.
        - draw 불가
        - 카드 뽑기 종료 불가(이미 종료 상태)
    - [X] Hit(2장을 받은 최초 상태, 게임을 지속할 수 있다.)
        - draw 가능
        - 한장 더 뽑았을 경우 BUST, HIT 두가지 상태 중 변경이 가능
        - 뽑기를 그만했을 경우 STAY상태로 변경 가능
    - [X] BUST(카드의 합이 21을 넘어가는 상태)
        - draw 불가
        - 카드 뽑기 종료 불가(이미 종료 상태)
    - [X] STAY(카드 뽑기를 종료한 경우)
        - draw 불가
        - 카드 뽑기 종료 불가(이미 종료 상태)

- [ ] 게임을 진행한 Player를 생성한다
    - [ ] Player는 이름과, 자신이 받은 플레잉카드들을 가지고 있다.
    - [ ] Player는 자신의 점수를 계산할 수 있다.
        - [ ] Ace가 존재할 시의 계산법을 고려한다.
            - [ ] 11을 더해도 `버스트`가 되지 않는다면 11을, 그렇지 않다면 1을 더한다.

- [ ] 게임을 진행한다.
    - [ ] 게임에 참여할 Player들을 입력받는다.
    - [ ] 시작 시 Player들에게 2장의 패를 분배한다.
        - [ ] BlackJack을 만족하는 유저가 존재하는지 확인하고, 만족한다면 게임을 종료한다.
    - [ ] Player 순서대로 게임을 진행한다.
        - [ ] 매번 카드를 draw 후 `버스트`가 됐는지 체크한다.
            - [ ] `버스트`라면 게임을 끝낸다.
            - [ ] `버스트`가 되지 않았다면 계속해서 카드를 뽑을지를 물어본다.
    - [ ] 모든 Player가 게임을 종료했다면 결과를 출력한다.
