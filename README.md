# kotlin-blackjack

## 읽기 전에
1. class A - class B(class B를 class A가 상속받음)
2. 번호는 순서가 아닌 기능을 구분하기 위해 사용된것이다.

## Card
1. 모양과 숫자를 가진다.

## Deck
1. 52장의 Card를 갖는다.
2. Deck은 셔플할수있다.
3. Card를 한장 주면 cards에서 한장 없어진다.

## People
1. addCard 메소드를 통해 하나의 카드를 받을수있다.
2. TotalScore를 통해 토탈 점수를 받는다.
3. 카드의 합이 21을 넘기면 bust한다.

## Player - People
1. 이름을 가지며 생성된다.

## TotalScore
1. Card를 받으면 점수를 반환한다.

## BlackJack
1. 게임 시작 전 플레이어들을 만든다.
2. dealer를 통해 deck을 셔플한다.
3. 모든 플레이어와 딜러는 카드 2장씩 받는다.
4. giveCard를 메소드를 통해 dealer에게 해당 People에게 카드를 주라고 명령할수있다.
5. Result를 만든다.

## Dealer - People
1. 카드의 합이 16을 넘겼는지 알려준다.
2. 카드를 준다.
3. deck을 셔플할수있다.

## Result
1. 플레이어 한명과 딜러의 승패를 알려준다.
2. 딜러의 승패는 Reuslt의 dealerResult에 기록된다.
