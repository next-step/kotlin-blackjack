# kotlin-blackjack

## 기능 요구사항 정리

- 카드
  - [x] 카드는 각 하트, 클로버, 다이아몬드, 스페이드 모양의 ACE, 2~10, King, Queen, Jack을 갖는다.
  - [x] 카드 모양상관없이 2~10은 번호만큼의 점수, King, Queen, Jack은 10점이 될 수 있다.
  - [x] 카드 ACE는 11점으로 계산하되 플레이어의 점수 총합이 21이 넘으면 1로 계산한다.
  - [ ] 카드는 각각 어떤 카드인지 한글 설명을 갖는다.
- 플레이어
  - [x] 플레이어는 N장의 카드를 가진다.
  - [x] 플레이어는 중복의 카드를 가질 수 없다.
  - [x] 플레이어는 가진 카드의 총합 점수를 계산할 수 있다.
  - [ ] 플레이어는 가진 카드의 총합이 21점이 이하라면 언제든지 1장의 카드를 추가로 뽑을 수 있다.
- 카드팩
  - [ ] 카드팩은 플레이어에서 카드를 지급할 수 있다.
  - [ ] 카드팩은 한 게임에서 플레이어 상관없이 동일한 카드를 지급하지 않는다.
- 게임 라운드
  - [ ] 게임 라운드가 시작되면 모든 플레이어는 카드 2장을 지급받는다.
  - [ ] 플레이어가 원하면 카드 1장을 추가로 지급한다.
