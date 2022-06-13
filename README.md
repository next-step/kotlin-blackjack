# 3주차 : kotlin-blackjack

## Step2 : 블랙잭

### 기능 요구사항

- 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
    - 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
    - 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수
      있다.

### 프로그래밍 요구사항

- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

### 할일

- [x] 쉼표로 구분된 참여자명을 입력받는다.
- [x] 시작 시 각 인원에게 2장씩 랜덤한 카드를 나눠준다.
- [x] 카드가 제공될때 마다 보유한 카드를 출력한다.
- [x] 보유한 카드 수보다 참여하는 인원이 많으면 에러를 리턴한다.(인원수*2<=52)
- [x] 보유한 카드개수가 없을때는 에러문구를 출력하고 바로 결과를 리턴한다.
- [x] 한 덱은 52개의 카드를 가진다.
- [x] 52개의 카드 중 랜덤하게 하나를 가져온다.
- [x] 플레이어는 랜덤한 카드 2장을 받아서 게임을 시작한다.
- [x] 플레이어는 새로운 카드를 받을 수 있다.
- [x] 플레이어는 새로운 카드를 받았을 때 hit/bust 상태로 나눠진다.
- [x] 나눠진 카드의 합을 구한다.

## Step1 : 코틀린DSL

### 실습내용

- 확장함수 사용
- infix 사용
- get 메서드에 대한 관례
- 람다를 괄호밖으로 빼내는 관례
- 수신 객체 지정 람다 (apply)

```kotlin
introduce {
    name("윤도현")
    company("카카오")
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

### 할일

- 코드리뷰 반영
    - [x] list 의 기본값을 사용해보기 ([review](https://github.com/next-step/kotlin-blackjack/pull/185#discussion_r886323018))
    - [x] selaed class 활용 ([review](https://github.com/next-step/kotlin-blackjack/pull/185#discussion_r886325117))
