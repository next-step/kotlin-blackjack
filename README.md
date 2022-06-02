# kotlin-blackjack
***
## 코틀린 DSL
아래와 같은 형식의 코틀린 DSL을 개발한다
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
- [x] introduce는 name, company, skills, languages를 받는다
- [x] name과 company는 문자열을 입력으로 받는다
- [x] skills는 soft와 hard를 받는다
  - [x] soft 함수
  - [x] hard 함수
- [x] languages는 언어와 그에 따른 레벨을 입력으로 받는다
  - [x] infix 함수 level이 필요하다

***
## 블랙잭
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- [x] 카드의 숫자 계산은 카드 숫자를 기본으로 한다
  - [x] 카드는 모양, 숫자(혹은 알파벳)으로 되어있다
  - [x] Ace는 1 또는 11로 계산할 수 있다
  - [x] King, Queen, Jack은 각각 10으로 계산한다.
- [ ] 게임을 시작하면 플레이어는 두 장의 카드를 지급 받는다 
  - [ ] 52장의 카드덱이 존재
  - [ ] 랜덤하게 두 장 지급
- [ ] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 
- [ ] 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
  - [ ] 이미 뽑힌 카드 덱에서 뽑는다