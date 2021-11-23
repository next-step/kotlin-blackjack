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

* [] Players(게임 참가자들)
    * [] names 를 나누어서 players 생성  
    * [] 딜러는 넣지 않는다. -> 공수비용 따졌을 때 이게 확장성 높다고 판단    
* [] Player(게임 참가자)
    * 공통 인터페이스
        * [] match(other: Player) : GameResult
            * [] state.match(other.state) 그대로 호출 
            * [] State 에 비교 전략 패턴 넣기(Running 익셉션, Finished 호출 가능)
                * [] 전략패턴 - MatchStrategy.excute(this: PlayerState, state: playerState): GameResult
                * [] BustMatchStrategy : Bust가 아닌 경우 무조건 Lose 반환 
                * [] BlackJackMatchStrategy : 블랙잭이 아닌 경우 무조건 WIN 반환 
                * [] StayMatchStrategy : 블랙잭이면 Lose, Stay인 경우 스코어 비교, Bust인 경우 승리  
        * [] draw(deck, 카드 뽑기 전략): Player
            * [] 뽑기 전략으로 처음 뽑기 또는 히트 뽑기 가능
            * [] 딜러일 경우, 17이상이 되면 stay 이나 bust로 변환 -> Hands.isOverDealerStandard()
            * [] 유저의 경우, 21이상이 되면 bust() 호출 -> Hands.isOverMaximum()
        * [] isFinish() : 현재 상태가 끝났는지 반환
    * [] Dealer :
        * draw(deck, 카드 뽑기 전략)
            * [] 카드 뽑기 전략 ->
            * [] 딜러는 카드를 뽑았을 때 17이상 21이하의 경우 Stay 로 변환
            * [] 딜러는 카드를 뽑았을 때 21초과인 경우 Bust로 변환
    * [] GamePlayer :
        * draw(deck, 카드 뽑기 전략)
            * [] 커멘드가 true 이면 draw 호출하여 GamePlayer 반환
            * [] 커멘드가 false 이면 stay 호출하여 GamePlayer 반환
            * [] 커멘드는 ui 도메인이므로 draw
    * [] Hands(추가)
        * [] Hands.isOverDealerStandard() -> Dealer.STANDARD 참조해서 넘는지 파악
        * [] Hands.isOverMaximum() -> Score.isOverMaximum 그대로 사용하면 될 듯
* [] MatchResult : 무승패
    * WIN, DRAW, LOSE
    * 승/패 : `Stay vs Bust`, `BlackJack vs Bust`, `BlackJack vs Stay`, `Stay(score) > Stay(score)`
    * 무 : `Bust vs Bust`, `BlackJack vs BlackJack`, `Stay(score) vs Stay(score)`
    * 상태 비교후, 동일한 경우 Score 비교  
* [] MatchResults : 결과들

    
## 상태 패턴 설계

**상태**

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
    
