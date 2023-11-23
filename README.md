# kotlin-blackjack

## 기능 요구 사항

 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
  - 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
  - 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.


## 기능 목록

### 플레이어
- [] 카드를 뽑는다.
- [] 카드를 그만 받는다.

### 블랙젝 게임
- [] 딜러와 플레이어가 있어야 시작된다.
- [] 플레이어는 최소 2명 이상이어야 한다.

### 트럼프 카드
- [] 모양과 문자 조합이 아니라면 예외를 던진다.
- [] 48장을 가진다.(모양 별 12개씩)

### 딜러
- [] 카드를 셔플한다.  
- [] 카드를 배포한다.
- [] 결과를 계산한다.

### inputView
- [] 플레이어를 입력받는다.
- [] 카드 수집 여부를 받는다..

### output
- [] 플레이어가 가지고 있는 카드를 보여준다.
- [] 플레이어 카드 계산값을 보여준다.
