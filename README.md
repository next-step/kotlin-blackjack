# kotlin-blackjack
## 기능 요구사항
### 게임
- 게임에 참여할 사람의 이름을 입력받는다.
- 게임을 시작하면 각 플레이어는 두 장의 카드를 지급 받는다.
- A는 1또는 11로 계산할 수 있다.
- J, Q, K는 10으로 계산된다.
- 플레이어는 카드 숫자의 합이 21보다 작으면, 한 장의 카드를 더 받을지 선택할 수 있다.
- 딜러는 처음에 받은 2장의 합계가 16 이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17 이상이면 추가로 받을 수 없다.
- 모든 플레이어가 카드를 더 받지 않으면 게임이 종료된다.
- 게임이 종료되면 각 플레이어가 가진 카드와 카드 숫자의 합을 출력한다.
- 게임이 종료되면 플레이어별 최종 승패를 출력한다.
- 딜러의 점수와 비교해서 동점이면 무승부, 딜러보다 높으면 이기고 낮으면 지게 된다.
- 플레이어의 점수가 21을 초과하면 딜러의 점수와 상관없이 패배한다.
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.

### 카드
- 덱 하나의 초기 카드 수는 52장이다.
- 카드의 끗수는 A, 2~10, J, Q, K 13개로 이루어져 있다.
- 카드의 무늬는 스페이드, 하트, 다이아몬드, 클로버 4개로 이루어져 있다.
