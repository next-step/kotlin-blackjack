# kotlin-blackjack

## Step1 코틀린 DSL

- 자기소개(introduce) DSL을 작성합니다.
```
// DSL 예시)

introduce {
  name("김정욱")
  company("kakao")
  skills {
    soft("성실함")
    soft("능동성")
    soft("열정")
    hard("spring webflux")
    hard("kotlin")
    hard("nextjs")
    hard("typescript")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
```


## 블랙잭(blackjack)

### 기능 요구사항
 - 딜러와 플레이어는 매 턴마다 카드를 받을지 결정하며 카드의 합이 21 또는 21에 가까운 사람이 승리
 - 처음에 플레이어는 2장의 카드를 받으며 합이 21을 넘지 않을 경우 얼마든지 카드를 뽑을 수 있다
 - 기본으로 카드에 있는 숫자로 계산되지만, Ace는 1 또는 11로 King/Queen/Jack은 각각 10으로 계산


### 기능 목록 step2)
 - [ ] 카드는 다이아몬드 / 하트 / 클로버 / 스페이드가 각각 13개의 끗수로 이루어져있다
 - [ ] King, Queen Jack 카드는 10점 / Ace 카드는 1또는 11점을 가질 수 있다 <br />
    ACE(1 or 11) / TWO(2) / THREE(3) / FOUR(4) / FIVE(5) / SIX(6) / SEVEN(7) / EIGHT(8) <br /> 
    / NINE(9) / TEN(10) / JACK(10) / QUEEN(10) / KING(10)

 - [ ] 플레이어는 '카드 리스트'와 '카드 총합 점수' 정보를 가지고 있으며 턴마다 변경 가능
 - [ ] 게임을 시작하면 플레이어는 한턴씩 돌아가며 차례를 가진다
 - [ ] 최초 턴에 플레이어는 2장의 카드를 지급받는다
 - [ ] 딜러와 플레이어는 매 턴마다 카드를 뽑거나 뽑지않거나 2가지 행동을 할 수 있다
 - [ ] 카드를 뽑으면 합이 21이 넘지 않는지 체크해야 한다
 - [ ] 카드를 뽑고 21이 넘으면 다음 차례부터 카드를 뽑을 수 없습니다
 - [ ] 같은 라운드를 기준으로 모든 플레이어가 카드를 뽑지 못하거나 스테이라면 게임이 종료




