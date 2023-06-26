# kotlin-blackjack

## 1단계

### 요구 사항
- 아래의 내용을 DSL로 만들어보는 실습을 진행한다
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

## 2단계

### 요구 사항
- 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- 숫자는 에이스, 2~10, j, q, k까지 총 13개, 모양은 하트, 다이아, 클로바, 스페이스까지 총 4개로, 카드는 총 52장이다.
  - j, q, k는 10을 의미한다
  - 에이스는 1 또는 11 중 참여자에게 유리한 값으로 사용된다.
      - ex 1) 에이스와 j를 갖는 경우, 에이스를 1로 선택하면 에이스(1) + j(10) = 11, 에이스를 11로 선택하면 에이스(11) + j(10) = 21이므로 11을 선택한다.
      - ex 2) 에이스, 3, k를 갖는 경우, 에이스를 11로 선택하면 에이스(11) + 3 + k(10) = 24이므로, 21을 초과하지 않기 위해 1을 선택한다.

### 구현 사항
- [x] 52장의 카드를 표현한다.
- [x] 게임에 참여할 사람의 이름을 쉼표 기준으로 분리하여 입력 받는다. 단, 잘못된 입력은 고려하지 않는다. (ex. 이름 공백, 중복된 이름)
- [x] 카드는 게임 시작시 섞는다.
- [x] 각 참여자들에게 2장씩 카드를 분배한다.
- [x] 각 참여자들이 받은 두장의 카드를 출력한다.
- [x] 먼저 입력된 참여자부터 카드를 더 받을지 입력 받는다. 단, 잘못된 입력은 고려하지 않는다. (ex. y대신 yes 입력)
- [x] 참여자가 y로 대답하면, 참여자는 카드를 한장 더 배부 받는다.
- 다음의 경우 다음 참여자로 순서가 넘어간다.
  - [x] 더이상 받지 않겠다는 의사를 밝힌다. (n 입력)
  - [x] 카드를 추가로 받았는데 21을 초과 한다. (bust 상태이며, 이 부분은 2단계의 요구 사항이 아니므로 따로 상태 처리를 하지는 않고 멈추기만 한다.)
- [x] 모든 참여자의 카드 발급이 완료된 경우 각 참여자의 카드들과 합산 점수를 출력한다.

## 3단계

### 요구사항
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### 구현 사항
- [x] 딜러를 추가한다.
- [x] 각 참여자들에게 2장씩 카드를 분배할 때, 딜러에게도 카드를 2장 분배한다.
- [x] 각 참여자들이 받은 두장의 카드를 출력할 때, 딜러의 카드도 출력한다. 이때, 딜러는 1장만 출력한다. (나머지 한장은 게임 규칙 상 숨김)
- 모든 참여자들의 카드 받기가 완료되면, 마지막으로 딜러에게 순서가 돌아간다.
  - [x] 딜러의 카드 합이 16 이하면 한장의 카드를 더 받는다.
  - [x] 딜러의 카드 합이 17점 이상이면 카드를 받지 않는다.
- [ ] 딜러가 21을 초과하면, 살아남은 플레이어들은 점수에 관계 없이 승리한다.
- [ ] 게임이 종료되면, 결과 값에 승패 유무를 추가한다.
- [ ] 게임 종료 결과를 출력할 때 승패 유무도 출력한다.
