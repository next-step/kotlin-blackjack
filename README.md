# kotlin-blackjack

## Requirements for the step 3

- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## Function list

- [X] Dealer 용 HitDecisionMaker (처음받은 2장의 합계 <=16 이면 true)
- [] 딜러가 21 초과시 게임종료
- [] 게임 결과 리턴 기능 
- [] 누적 승패 기록  - Record 기능 




## Requirements for the step 2

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며
- 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다
- 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

## Function list

- [X] 모양(SPADES, DIAMONDS, HEARTS, CLUBS)과 숫자( A,1,2...10,J,Q,K ) 를 갖는 카드 13*4 (52자) 구성
- [X] 52장의 카드를 랜덤하게 섞는 기능
- [X] 각 참여자에게 2장씩 배분
- [X] Hit ,Stay 에 따라 카드 배분
- [X] 점수 계산
- [X] Bust, BlackJack, Win /Lost 결정
- [X] 참여자 이름 입력
- [X] 결과 출력
