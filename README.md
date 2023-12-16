# kotlin-blackjack

## 미션 피드백 링크

- Step 1: [https://github.com/next-step/kotlin-blackjack/pull/702](https://github.com/next-step/kotlin-blackjack/pull/702)
- Step 2: [https://github.com/next-step/kotlin-blackjack/pull/704](https://github.com/next-step/kotlin-blackjack/pull/704)
- Step 3: [https://github.com/next-step/kotlin-blackjack/pull/705](https://github.com/next-step/kotlin-blackjack/pull/705)

## 미션 내용

### STEP 2

<details>
<summary>접기/펼치기</summary>
<div markdown="1">

#### [요구 사항 분석]

- 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 승자
- Ace는 1 또는 11 중에 더 유리한 숫자로 계산
- King, Queen, Jack은 각각 10으로 계산
- 게임 시작 시 플레이어는 두 장의 카드를 지급 받음
- 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 승리
- 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있음
- 21을 넘을 경우 카드는 더 뽑지 못하고 패배한다

#### [기능 목록]

- [x] 게임에 참여할 사람을 입력받는다.
- [x] 게임 카드는 총 52장이다. (스페이드, 클로버, 하트, 다이아 : 2~10, Ace, J, Q, K)
- [x] 게임 시작 시 각 참여자들은 2장의 카드를 받는다.
- [x] 카드를 받을 경우 각 참여자들이 가진 패를 출력한다.
- [x] 카드의 총 숫자가 21 이하면 계속 카드를 뽑을 수 있다.
- [x] Ace 카드는 1과 11 중에 21에 더 가까운 숫자가 될 수 있게 선택된다.
- [x] 카드의 총 숫자가 21 초과이면 더이상 카드를 뽑을 수 없다.
- [x] 카드의 총 숫자가 21 초과일 때 Ace 카드가 있을 경우, Ace 카드는 1로 계산된다.
- [x] 카드를 뽑을 때 마다 현재 패를 출력한다.
- [x] 한 플레이어가 더이상 카드를 받지 않으면, 다음 참가자가 게임을 시작한다.
- [x] 게임이 종료되면 각 플레이어의 현재 패와 결과 점수를 출력한다.
- [x] 모든 엔티티를 작게 유지하고, indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다.

</div>
</details>

<br>

### STEP 3

#### [피드백]

- [x] View 계층은 입출력 관련 책임만 갖도록 변경한다. (View계층에서 도메인 객체를 생성하는 행위를 이관한다)
