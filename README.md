# kotlin-blackjack

## 기능 요구사항

### Step2

블랙잭 게임을 변형한 프로그램을 구현한다. <br>
블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- [X] 카드의 숫자 계산은 카드 숫자를 기본으로 한다.
    - [X] 예외로 Ace는 1 또는 11로 계산할 수 있다.
    - [X] King, Queen, Jack은 각각 10으로 계산한다.
- [X] 게임을 시작하면 플레이어는 두 장의 카드를 지급 받는다.
    - [X] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
    - [X] 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### Step3

- [X] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다.
    - [X] 2장의 합게가 17점 이상이면 추가로 받을 수 없다.
- [X] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
- [X] 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### Step4

- [X] 플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다.
- [X] 플레이어가 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.
- [X] 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
- [X] 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
- [X] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

## 프로그래밍 요구사항

- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.
