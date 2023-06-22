# kotlin-blackjack


## 🚀 1단계 - 코틀린 DSL

### TODO: 
    
    introduce {
        name("박세준")
        company("에스이랩")
        skills {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }
        languages {
            "Korean" level 5
            "Japanese" level 3
            "English" level 1
        }
    }
 introudce 메소드를 통해 Person 을 리턴한다!
 
## 기능 목록
- [x] 스킬을 입력받을 수 있다.
- [x] 언어를 입력 받을 수 있다.

## 🚀 2단계 - 블랙잭


## TODO : 
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

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

## 기능 목록
- [x] 게임에 참여할 사람의 이름을 입력받는다. -InputView
- [x] 참여자들에게 카드를 두 장 나누어 진다. -Dealer
- [x] 참여자들은 여러 장의 카드를 가질 수 있다. - Player
- [x] 덱에는 카드가 52장 저장되어 있다. -Deck
- [ ] 덱에는 한장을 뽑는다. 카드는 중복 될 수 없다. -Deck
- [ ] 카드는 번호와 심볼을 가지고 있다. - Card
- [ ] 참여자는 추가로 카드를 받는 Hit과 더 이상 받지 않는 Stay를 선택할 수 있다. - Player
- [ ] 참여자 손패의 카드합이 21이상이라면 Bust 더이상 카드를 받지 못한다. - HandsCalculator
- [ ] 손패의 카드 합을 더한다. J, Q, K는 10이다. A는 1이거나 11이다. Hands
- [ ] 손패 총합 값을 계산한다. - HandsCalculator
- [ ] 결과를 발표한다. -Game
