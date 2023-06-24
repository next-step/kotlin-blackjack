# kotlin-blackjack

## Step 1. 코틀린 DSL

- 아래 DSL을 동작하게 하는 코드를 작성한다.

```
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

## Step 2. 블랙잭

- 카드의 숫자 계산은 카드 숫자를 기본으로 함
- 예외로 Ace는 1 또는 11로 계산할 수 있음
- King, Queen, Jack은 각각 10으로 계산
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받음
- 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
- 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
