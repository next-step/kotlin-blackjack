# kotlin-blackjack

# 2단계 - 블랙잭

### 기능 요구사항

* 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
* 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
* 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 실행 결과

```
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

### Todo

- [x] 게임에 참여할 사람 입력받기 - InputView
- [x] 게임에 참여할 사람 이름 저장 - Player
- [x] 카드 리스트 만들기 - Card
- [x] player에게 카드를 나눠주기 - BlackJack
- [x] player 카드 출력 - ResultView
- [x] 점수 계산하기 (21이 넘으면 카드를 더 받을 수 없다.) - blackjack
- [x] n라고 입력할 때가지 게임 진행하기 - Main?, InputView
- [x] 게임 결과 출력하기 - ResultView

# 3단계 - 블랙잭(딜러)

### 기능 요구사항

* 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
* 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
* 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### 실행 결과

```
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

### Todo

- [x] 딜러 객체를 추가한다 - dealer
- [x] 딜러도 카드를 받는 부분을 추가한다 - blackjack
- [x] 딜러의 카드도 출력한다 - ResultView
- [x] 딜러의 점수 계산과 카드를 더 받아야 하는지 여부를 구현한다. - blackjack, dealer
- [x] 승패를 계산한다. - blackjack, player,ranks,rank
- [x] 승패를 출력한다. - ResultView

# 4단계 - 블랙잭(베팅)

### 기능 요구사항

* 플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다.
* 단, 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.
* 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
* 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.
* 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
* 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

### 실행 결과

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi의 배팅 금액은?
10000

jason의 배팅 금액은?
20000

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

## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```

### Todo

- [x] 각 플레이어는 베팅 금액을 입력받는다. -InputView, Player
- [x] 베팅과 플레이어를 저장한다. - player
- [x] 베팅금액으로 수익을 계산한다 - Rank
- [ ] 최종 수익을 출력한다. - ResultView
- [ ] 3개 이상의 인스턴스 변수를 가진 클래스를 정리한다. - BettingPlayer?
- [ ] 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

