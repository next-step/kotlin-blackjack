# kotlin-blackjack

## 게임 룰

카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.

게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.

딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.

게임을 완료한 후 각 플레이어별로 승패를 출력한다.


## 블랙잭 게임 승리 룰
- 플레이어가 bust라면 항상 딜러가 승리한다.
- 플레이어가 살아있고 딜러가 bust라면 플레이어가 승리한다.
- 한쪽만 블랙잭이면 블랙잭인 쪽이 승리한다.
- 그 외에는 점수가 높은 쪽이 승리한다.

## 추가된 블랙잭 베팅 룰
- 플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다.
- 플레이어의 카드 합이 21을 초과할 경우 베팅 금액을 모두 잃게 된다.
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 
- 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다. (무승부)
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.
