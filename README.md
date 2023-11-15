# kotlin-blackjack

## 블랙잭

### 기능 요구사항

#### view
- [x] 쉼표를 기준으로 참여자의 이름을 구분한다.

#### rule
- [x] 게임을 시작하면 두 장의 카드를 지급받는다.
- [x] 21을 넘지 않은 경우 얼마든지 카드를 계속 뽑을 수 있다.
- [x] 21을 넘으면 자동으로 카드를 뽑을수 없는 상태가 된다.

#### point
- [x] 카드 합이 21 또는 21에 가까운 숫자를 가지는 쪽이 승리한다. (21을 넘기면 안된다)
- [x] 카드는 숫자 계산은 카드 숫자를 기본으로 한다.
- [x] 예외로 Ace는 1 또는 11로 계산할 수 있다.
- [x] King, Queen, Jack은 각각 10으로 계산한다.

#### develop
- [x] indent depth를 2를 넘지 않도록 구현한다.
- [x] 모든 엔티티를 작게 유지한다.
- [x] 함수(또는 메서드)의 길이가 15라인을 넘지 않도록 한다.


## 블랙잭 (딜러)

### 기능 요구사항

#### rule
- [x] 게임 플레이어에 딜러도 포함된다.
- [x] 딜러는 게임 시작시 2장의 카드를 지급받는다.
- [x] 딜러의 카드는 한 장만 오픈한다.
- [x] 딜러는 처음 받은 두 장의 카드가 16점 이하일 경우 반드시 1장의 카드를 추가로 받아야 한다.
- [x] 딜러는 처음 받은 두 장의 카드가 17점 이상일 경우 추가로 카드를 받을 수 없다.
- [x] 딜러가 21을 초과하면 그 시점까지 남아있는 플레이어들은 가지고 있는 패와 상관없이 승리한다.

#### view
- [x] 게임 완료 후 플레이어별 승패를 출력한다.

#### point
- [x] ~~딜러와 동점일 경우는 패배로 처리한다.~~
- [x] ~~딜러와 동점일 경우에는 딜러는 승리로 처리하지 않는다.~~
- [x] ~~딜러와 동점일 경우에는 딜러는 패배로도 처리하지 않는다.~~

#### develop
- [x] 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- [x] 딜러와 플레이어에서 발생하는 중복 코드를 제거한다.


## 블랙잭 (베팅)

### 기능 요구사항

#### rule
- [x] 게임을 시작할 때 베팅 금액을 정한다.
- [x] 카드를 추가로 뽑아서 21을 넘기면 베팅 금액 모두 잃는다.
- [x] 처음 두 장의 카드 합이 21인 경우 블랙잭이 되면 베팅 금액의 1.5배를 받는다.
- [x] 딜러와 플레이어가 모두 블랙잭인 경우 베팅 금액을 돌려받는다.
- [x] 딜러가 21을 초과하면 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

#### betting
- [x] 최종 수익은 베팅 결과 - 베팅 금액이다.  
- [x] 딜러와 동일한 점수일 경우 베팅 금액을 돌려받는다.  
예를 들어, A와 B가 각각 1000, 2000원을 베팅하고 게임 결과 A가 승리했다면 A는 베팅 금액 1000에 승리 보상 1000, 총 2000을 받게 되고, B는 모든 금액을 잃게 된다. 나머지 1000은 딜러의 소유가 된다. 그래서 최종 수익은 A: 1000, B: -2000, 딜러: 1000이다.   
동일한 베팅으로 A가 블랙잭이 된다면 1000에 1500을 추가로 받게 되어 총 2500을 받게 되고 딜러는 500을 얻게 된다. 따라서 최종 수익은 A: 1500, B: -2000, 딜러: 500이다.  
