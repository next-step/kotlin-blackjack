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


 2장의 카드를 나눠주는 행위를 "딜"이라
```
