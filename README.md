# kotlin-blackjack

## 🚀 1단계 - 코틀린 DSL

```kotlin
dsl.introduce {
    name("최차영")
    company("회사")
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

## 🚀 2단계 - 블랙잭

### 기능 요구사항

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 기능 구현 목록

- [X] 카드
    - [X] Ace, 2 ~ 10, King, Queen, Jack의 숫자값 중 하나를 가진다.
    - [X] 하트, 다이아, 스페이드, 클로버 4 종류의 모양 중 하나를 가진다.
- [X] 카드 리스트
    - [X] 카드들을 가질 수 있고, 카드 수를 가져온다.
    - [X] 카드를 추가할 수 있다.
    - [X] 카드의 점수를 카운팅한다.
    - [X] 카드의 합이 21이 넘으면 ACE 카드는 11로, 넘지않으면 1로 계산된다.
- [X] 덱
    - [X] 전체 카드 총 52장을 가진다.
- [X] 딜러
    - [X] 최초 52장의 덱을 가진다.
    - [X] 딜러는 보유한 카드를 셔플할 수 있다.
    - [X] 참가자에게 카드를 한장씩 분배할 수 있다.
- [X] 참가자
    - [X] 이름을 가진다.
    - [X] 딜러가 분배한 카드를 가질 수 있다.
    - [X] 가지고 있는 카드 점수를 카운팅한다.
    - [X] 카드를 추가로 받을 수 있다.
- [X] 게임
    - [X] 딜러와 참가자들을 가진다.
    - [X] 게임 시작시 2장의 카드를 랜덤하게 참가자들에게 분배한다.
- [X] 입력
    - [X] 플레이어 이름을 쉼표로 구분하여 입력받는다.(최대 8명)
    - [X] 추가로 카드를 받을지의 여부를 입력받는다.
- [X] 출력
    - [X] 참가자가 보유한 카드들을 출력한다.
    - [X] 게임 시작시 2장을 분배하고 결과값을 출력한다.
    - [X] 게임 결과를 출력한다.
