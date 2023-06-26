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

## Step 3. 블랙잭(딜러)

- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다.
- 딜러는 처음에 받은 2장의 합계가 17점 이상이면 추가로 받을 수 없다.
- 딜러가 21점을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.