# kotlin-blackjack

# 1단계 - 코틀린 DSL
## 요구사항
```kotlin
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

# 2단계 - 블랙잭
## 기능 요구사항
> 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.   
  21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.

- [x] 플레이어 인터페이스를 가진다.
  - [x] 이름과 덱, 점수 프로퍼티를 가진다.
    - [x] 카드 추가 기능을 정의한다.
    - [x] 카드 점수 계산 기능을 정의한다.
    - [x] 카드 추가 가능여부 조회 기능을 정의한다.
- [x] 플레이어를 구현하는 게이머 객체를 가진다. 
  - [x] 현재 점수가 21점 이상인 경우 카드 추가시도시 예외를 던진다. 
  - [x] 현재 점수가 21점 미만인 경우 카드를 추가할 수 있다.
- [x] 플레이어 일급 컬렉션 객체를 가진다.
- [x] 카드 인터페이스를 가진다.
  - [x] 심볼, 이름, 점수 프로퍼티를 가진다.  
  - [x] 점수 조회 기능을 정의한다. 
- [x] 카드를 구현하는 인물카드 객체를 가진다.
  - [x] 인물 카드는 모두 10점을 반환한다.
  - [x] 이름은 심볼 타입의 첫 글자를 소문자로 반환한다.
- [x] 카드를 구현하는 에이스 카드 객체를 가진다.
  - [x] 이름은 A를 반환한다.
  - [x] 포인트는 1점과 11점중 현재점수와 합산해 21점에 가까운 점수를 반환한다.
- [x] 카드를 구현하는 숫자 카드 객체를 가진다.
  - [x] 2 ~ 9 사이의 값을 전달하면 정상적으로 생성된다.
  - [x] 2 ~ 9 이외의 값을 전달하면 예외를 던진다.
  - [x] 숫자가 이름이 된다.
  - [x] 포인트는 오직 자기 숫자 정보를 반환한다.
- [x] 카드 일급 컬렉션 덱 객체를 가진다. 
  - [x] 카드 추가를 할 수 있다.
  - [x] 카드 제거를 할 수 있다.
  - [x] 점수 계산을 할 수 있다.
- [x] 게임 진행 컨트롤러를 가진다. 
  - [x] 최초 플레이어들에게 2장씩 배분해줄 수 있다. 
  - [x] 플레이어를 순회하며 가능한 경우 카드를 뽑을지 물어본다.
    - [x] 플레이어의 현재 점수가 21점 미만인 경우 가능하다고 판단한다.
    - [x] 플레이어의 현재 점수가 21점 미만이지만, 원하지 않을 경우 다음 플레이어로 넘어간다.
    - [x] 플레이어의 현재 점수가 21점 이상인 경우 다음 플레이어로 넘어간다.
    - [x] Y, N외의 답변을 하면 다시 물어본다.
  - [x] 순회가 끝나면 결과를 출력한다.


## 프로그래밍 요구 사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

## 실행 결과
```text
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi, jason에게 2장의 나누었습니다.
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

```

# 3단계 - 블랙잭(딜러)
## 기능 요구사항
> 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.   

- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
  - [ ] 플레이어를 구현하는 딜러 객체를 가진다.
    - [ ] 점수가 16점 이하이고, 최초 한 번 이후 분배받은 적이 없을 경우 카드를 추가할 수 있다.
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
  - [ ] 승패 계산 인터페이스를 가진다. 
    - [ ] 승패 계산 기능을 정의한다.
    - [ ] 승패 계산 인터페이스를 구현하는 승패 계산기 구현체를 가진다.
      - [ ] 딜러가 21점을 초과하면 플레이어어 점수에 상관없이 승리로 평가한다.
      - [ ] 딜러가 21점 이하라면 각각의 플레이어와 승패를 계산한다.
    - [ ] 승패 계산결과에는 딜러는 몇승 몇패를 했는지 기록된다.
    - [ ] 승패 계산결과에는 플레이어는 승패 결과가 기록된다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.
  - [ ] 딜러와 플레이어의 승패 결과를 출력한다.
- 이외에는 2단계와 동일하다.

## 프로그래밍 요구 사항
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.
- 나머진 2단계와 동일하다

## 실행 결과
```text
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장의 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```
