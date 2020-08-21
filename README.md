### kotlin-blackjack(블랙잭)

### 1. 핵심 로직

##### - 카드
- 카드점수와 카드모양을 짝으로 갖는 객체다
- 카드점수를 반환하는 기능이 있다
- 카드가 ACE인지 확인하는 기능이 있다
- 카드점수의 이니셜("ACE"면 "A")과 카드모양("하트")를 조합해 "A하트"와 같은 문자열을 반환하는 기능이 있다

##### - 카드 목록
- 카드 집합(목록)을 갖는다
- 새로운 카드를 자신의 카드 목록에 더하는 기능이 있다
- 카드 목록의 크기를 구하는 기능이 있다
- 점수가 게임 최고점수(21)보다 큰지 확인하는 기능이 있다
- 점수가 17보다 작은지 확인하는 기능이 있다 (딜러 플레이 관련)
- 각 카드의 점수 합계를 구하는 기능이 있다
    - 카드 점수 객체의 기능을 이용한다
- ACE 카드를 가지고 있는지 확인하는 기능이 있다
- 목록의 첫 번째 카드를 반환하는 기능이 있다

##### - 카드 점수
- 2부터 10까지, ACE, JACK, QUEEN, KING의 상수(객체)를 가진다
- 각 상수(객체)는 알맞은 카드 점수를 가지고 있다
- 카드의 이니셜("ACE"면 "A")을 반환하는 기능이 있다
- 각 카드의 점수 합계를 구하는 기능이 있다
    - 합계가 11 이하이면서 동시에 ACE를 갖고 있는 경우
    -> ACE의 점수를 11로 사용할 수 있다 (그렇지 않은 경우엔 ACE의 점수: 1)

##### - 카드 모양
- 4가지 모양의 상수(객체)를 가진다 (4가지 모양 : 하트, 클로버, 스페이드, 다이아몬드)
- 각 상수(객체)는 알맞은 모양의 이름을 갖는다

##### - 덱(카드팩)
- 총 52장의 카드를 갖고 있는 객체다
- 모든 카드를 섞는 기능이 있다
- 한 장의 카드를 제공하는 기능이 있다
    - 덱이 비어있을 때 새로운 덱을 사용한다
- 주어진 모양의 카드 팩을 만드는 기능이 있다

##### - 참가자
- 게임을 위해 카드 2장을 미리 받는 기능이 있다
- 조건을 충족할 때 카드를 뽑는 기능이 있다
- 카드 한 장을 뽑는 기능이 있다
- 자신의 점수가 게임의 최고점수(21) 이상인지 확인하는 기능이 있다
- 카드 개수를 반환하는 기능이 있다
- 카드 점수 합계를 반환하는 기능이 있다
- 카드 목록을 문자열로 반환하는 기능이 있다

##### - 딜러 
- 참가자를 구현하는 객체다 
- 덱을 갖는다 
- 카드 목록을 갖는다 
- 매치 결과 목록을 갖는다 (승,패) 
- 카드 한 장을 덱에서 꺼내 주는(반환하는) 기능이 있다 
- 자신이 2장의 카드를 가지면서 동시에 모든 플레이어에게 카드 두 장씩 준다
- 카드 점수 합계가 17 미만인지 확인하는 기능이 있다 
- 승리와 패배를 기록하는 기능이 있다 

##### - 플레이어
- 참가자를 구현하는 객체다
- 자신이 뽑은 카드 목록을 가지고 있다
- 매치 결과를 갖는다
- 게임을 시작할 때 베팅 금액을 정해야 한다
- 카드 한 장을 뽑을지 말지를 결정하는 기능이 있다
    - 대답이 y이고, 점수 합계가 게임 최고점수(21) 보다 크지 않다면 카드를 뽑늗다
- 카드를 한장 새로 뽑으면 갖고 있는 모든 카드의 목록을 반환하는 기능이 있다
- 카드 개수를 반환하는 기능이 있다
- 카드 점수 합계를 반환하는 기능이 있다
- 카드 목록을 반환하는 기능이 있다
- 게임에서 이겼는지 확인하는 기능이 있다

##### - 플레이어 목록
- 해당 인덱스의 플레이어를 찾는 기능이 있다
- 목록의 크기를 반환하는 기능이 있다
- 딜러와 모든 플레이어의 점수를 비교하는 기능이 있다
    - 딜러의 점수가 21보다 크면, 패와 상관없이 모든 플레이어는 승리한다
    - 플레이어의 점수가 21 또는 딜러 점수와 같거나, 딜러 점수에 비해 21에 가깝다면 승리한다
    - 그 외의 경우엔 패배한다
- 모든 플레이어의 이름과 카드목록을 문자열로 반환하는 기능이 있다
- 모든 플레이어의 이름을 문자열로 반환하는 기능이 있다 

##### - 게임
- 플레이어들의 목록을 갖는다
    - 플레이어들의 이름을 분리하여 플레이어들의 목록을 만든다
- 딜러를 갖는다
- 턴(뽑기 차례)를 변수로 갖는다
- 게임 시작시 딜러와 플레이어에게 카드 2장씩 지급한다
- 플레이어가 한 장의 카드를 뽑을 수 있게 한다
    - 선택권은 플레이어에게 넘긴다 (플레이어가 카드를 뽑을지 말지 결정)
    - 뽑기를 거절하거나, 플레이어의 점수가 21보다 높을 때 다음 플레이어에게 턴이 넘어간다
- 해당 턴의 플레이어를 찾는 기능이 있다
- 게임이 종료됨을 알리는 기능이 있다
    - 턴의 값과 게임 참가자의 크기가 일치할 때(즉, 턴이 다 돌았을 때) 
- 딜러가 마지막에 카드 뽑기에 참여하는 기능이 있다
    - 딜러의 점수가 16 이하일 때 카드를 한장 더 뽑는다
- 게임의 매치 결과를 딜러와 플레이어에게 확인시키는 기능이 있다 

##### 베팅 금액

##### 수익
- 카드 상황에 따라 알맞은 수익을 제공한다

##### 매치 결과
- 플레이어가 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다
- 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다

---
### 2. UI 로직

##### - 입력 담당 객체
- 게임 참가자들의 이름을 입력받는다 (쉼표를 기준으로 분리)
-  게임을 시작할 때 베팅 금액을 입력받는다.
- 각 참가자들에게 카드 한 장을 더 받을 건지 물어보고 대답을 입력받는다 (예는 y, 아니오는 n)
    - 위의 질문의 대답이 n일 때까지 질문을 반복한다

##### - 출력 담당 객체
- 각 참가자가 처음 가지고 있는 카드를 보여준다
- 참가자 한 명의 최신 카드 목록를 보여준다
- 모든 참가자의 활동이 끝나면 딜러의 플레이를 보여준다
- 게임이 종료되면, 점수 결과를 보여준다 
- 게임이 종료되면, 매치 결과를 보여준다 
