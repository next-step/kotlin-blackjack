# kotlin-blackjack

## Step1

- 스텝1에서의 미션 목표는 아래의 코틀린 DSL 을 돌아가게 DSL 펑션을 구현하는것이다

- 미션목표 DSL

```text
introduce {
  name("박재성")
  company("우아한형제들")
  skills {
    soft("A passion for problem solving")
    soft("Good communication skills")
    hard("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
```


## step 2 

### 요구사항
```text

```
### 용어집
```text

CARD : 카드 한장, RANK+SUIT 로 이루어짐
  - Cards : 카드 여러장의 일급컬렉트

PACK : 팩 : 53장 카드 한벌의 세트
  - 각각 13장으로 이루어진 4종류의 Suit 로 이루어짐
  
SUIT : 슈트 : 하트(♡), 다이아몬드(◇), 스페이드(♤), 클로버(♧) 4가지 종류가 있다

RANK :  각 카드의 순서 K (king), Q (queen), J (jack), 10, 9, 8, 7, 6, 5, 4, 3, 2, A(ace=1)로 이루어짐

Participants.dealing() : 게임이 시작할때 딜러가 모든 참가자들에게 2장의 카드를 나눠주는 행위를 "딜"이라
 
Player.hit() :  블랙잭에서 카드를 받는 행동은 "히트" 딜러가 플레이어에게 추가 카드를 주는 것을 말합니다. 

반대로 카드를 더 이상 받지 않고 현재의 카드로 결과를 결정하려면 "스탠드"라고 합니다. 
스탠드를 선택하면 더 이상의 추가 히트를 하지 않고 현재의 카드로 게임을 이어나갑니다.
```


### 수정사항
```text
- 카드를 한벌만 가지고 blackjack 을 플레이할꺼라 오해했습니다(약간 변명하자면 서양식 고도리라고 생각했었네요..) >> 이를 여러명이서도 블랙잭을 플레이할 수 있도록, 동일한 카드가 중복해서 나오는 것을 해결하였습니다. 

```

- 2차 수정
```text
- 코드 중 !! 를 requireNotNull 로 대체하고 에러메시지를 출력하게 수정하였습니다
- MODEL/DOMAIN 클래스 내부에 VIEW 클래스가 있는지 체크하고 수정 // PR 요청 전 regression 하기
- VIEW 클래스 내부에 MODEL/DOMAIN 로직이 담겨있는지 체크하고 수정   // PR 요청 전 regression 하기트
- 매직넘버, 매직스트링을 상수로 분리해내기 (특히 view, model)
```
