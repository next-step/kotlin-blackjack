# kotlin-blackjack

### 기능 요구 사항
블랙잭 게임 구현
- 카드의 숫자를 더한 값으로 계산, 
- 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임 시작 시 두 장의 카드 지급
- 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
- 21을 넘지 않을경우 원한다면 얼마든지 카드를 뽑을 수 있다.

### TODO
- 카드 덱 구성
  - 카드는 각 모양(다이아몬드, 하트, 스페이드, 클로버) 마다 1~10 + King, Queen, Jack + ACE 해서 13 * 4 
  - 52장
- 숫자 하나당 4 종류의 카드
- 게임에 참여할 사람 입력 받기
- 쉼표 기준으로 분리하기
- 플레이어 구성
- 무작위로 2장 나눠주기
- 21 이 넘지 않을 떄까지 카드 뽑기 가능(n 입력시 카드 뽑기 끝남)
- 점수 계산
- ACE는 1또는 11로 계산하기 때문에 2가지 값으로 계산
