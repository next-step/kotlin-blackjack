# kotlin-blackjack

## 요구 사항

-[x] 게임에서 사용될 카드는 조커를 제외한 52장이다 - 모든 종류, 숫자별 1장씩.
-[x] 게임에서 사용할 카드는 1장씩 순서대로 뽑을 수 있다.
-[x] 게임에서 사용할 카드가 충분하지 못할 경우 예외를 던진다.
-[x] 카드는 스페이드, 다이아몬드, 하트, 클로버 무늬로 구분된다.
-[x] 카드는 무늬별로 A, 2~10, J, Q, K 숫자를 갖는다.
-[x] 카드는 아래의 규칙을 바탕으로 값으로 계산된다.
    * A는 1 또는 11로 계산합니다.
    * 2 ~ 10은 각각의 숫자로 계산합니다.
    * J, Q, K는 10으로 계산합니다.
-[x] 플레이어는 카드를 빋아서 소유할 수 있다.
-[x] 심판은 카드들이 승리, 패배 조건을 만족하는지 확인한다.
    - 플레이어의 카드 총합이 21점이 가능할 때 - 승리
    - 플레이어가 모든 카드 총합 점수의 최솟값이 22점 이상일 때 - 패배
    - 그 외 - 게임 계속
-[ ] 블랙잭은 특정한 순서로 진행한다. 2~3 과정에서 플레이어가 21점을 초과하거나 21점에 도달하면 승부가 결정난다.
    1. 참석할 플레이어 이름들을 입력받는다.
    2. 플레이어들에게 순서대로 2장의 카드를 나눠준다.
    3. 플레이어들의 카드를 출력한다.
    4. 플레이어들에게 순서대로 카드를 더 받을지 말지 물어본다. 카드를 뽑을 때마다 플레이어들의 카드를 출력한다. 플레이어가 더 이상 받지 않을 경우 다음 플레이어에게 물어본다.
    5. 승부가 날 때까지 2-3을 반복한다.
-[ ] 위의 순서 중 2~3번 과정에서 플레이어의 점수가 아래 조건 중 하나를 만족하면, 모든 플레이어의 카드와 점수를 출력하고 게임을 종료한다.
    - 플레이어의 카드 총합이 21점이 가능할 때
    - 플레이어가 모든 카드 총합 점수의 최솟값이 22점 이상일 때
