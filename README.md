# kotlin-blackjack

## 1단계 - 코틀린 DSL
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

## 2단계 - 블랙잭

### 기능 요구사항

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 기능 목록

- [ ] 카드는 총 52 (A - K, 13장 x 4)장으로 구성된다.
  - [ ] 모양은 스페이드, 다이아몬드, 하트 클로버로 구성된다.
  - [ ] 숫자는 2 부터 9까지 존재한다
  - [x] King, Queen, Jack은 각각 10으로 계산한다
  - [x] Ace는 1 로 계산한다
  - [ ] Ace는 11로 계산할 수 있다
- [ ] 블랙잭 게임은 딜러와 플레이어의 카드 합이 21 또는 21에 가장 가까운 사람이 이긴다.
- [ ] 블랙잭 게임 시작시 플레이어와 딜러는 두장의 카드를 가진다.
- [ ] 게임 시작 후 플레이어와 딜러는 추가적으로 카드를 뽑을 수 있다
    - [ ] 21이 넘지 않을 경우 계속 뽑을 수 있다
