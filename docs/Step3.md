# 2단계 - 블랙잭

## 기능 요구사항

* 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
* 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
* 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## 힌트

* 상속을 활용해 중복을 제거해 본다.

```kotlin
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2 장의 나누었습니다.딜러: 3 다이아몬드
    pobi카드: 2 하트, 8 스페이드
    jason카드: 7 클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2 하트, 8 스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7 클로버, K스페이드

딜러는 16 이하라 한장의 카드를 더 받았습니다 .

딜러 카드 : 3 다이아몬드, 9 클로버, 8 다이아몬드 -결과: 20
pobi카드: 2 하트, 8 스페이드, A클로버 - 결과: 21
jason카드: 7 클로버, K스페이드 - 결과: 17

## 최종 승패
    딜러: 1 승 1 패
    pobi: 승
jason: 패
```

## 클래스 설계

* [x] Players(게임 참가자들)
    * [x] names 를 나누어서 players 생성
* [x] Player(참가자)
    * [x] `match(other: Player): MatchResult` : state.match(other.state)로 호출
    * [x] `isFinish(): Boolean` : 현재 상태가 끝났는지 반환
    * [x] `abstract draw(deck, 카드_뽑기_전략): Player` : 반환이 상위이므로 추상 메서드 선언 후 하위에서 구현하는 걸로
* [x] Dealer(딜러)
    * [x] `draw(deck, 카드_뽑기_전략): Player` : state.draw() 의 값에 따라 로직 변화 
        * [x] state.draw() 의 값이고 Hit 이고, 17이상 21이하인 경우 Hit
        * [x] 버스트, 블랙잭은 state.draw() 의 값으로 알아서 세팅
* [x] GamePlayer(게임 참가자)
    * [x] `draw(deck, 카드_뽑기_전략): Player` : 일반 방식대로 동작  
* [x] Hands(손패, 기존에 추가)
    * [x] isOverDealerDrawStandard -> 딜러 기준치를 넘는지 반환 
* [x] MatchResult(승부 결과)
    * [x] WIN, DRAW, LOSE
    * [x] 승/패 : `Stay vs Bust`, `BlackJack vs Bust`, `BlackJack vs Stay`, `Stay(score) > Stay(score)`
    * [x] 무 : `Bust vs Bust`, `BlackJack vs BlackJack`, `Stay(score) vs Stay(score)`
    * [x] 상태 비교후, 동일한 경우 Score 비교  
* ~~[] MatchResults : 결과들 -> 만들어도 사용할 일이 없음 -> 다시 빼내서 작업해야함~~
 
### 상태 패턴 적용   
* [x] Running
    * 공통 기능
        * [x] isFinished : false
        * [x] score : 호출 불가
        * [x] stay: Stay 변경 가능
    * [x] Ready
        * [x] draw :
            * [x] 한 개 -> Ready
            * [x] 두 개 21 -> 블랙잭
            * [x] 두 개 21미만 -> 히트
    * [x] Hit
        * [x] draw :
            * [x] 21 초과 : Bust
            * [x] 21 이하 : Hit
* [x] Finish
    * 공통 기능
        * [x] isFinished : true
        * [x] score : Hands 호출
        * [x] stay: Stay 변경 불가
        * [x] draw : 카드 추가 불가
    * [x] Stay
    * [x] BlackJack
    * [x] Bust
    
