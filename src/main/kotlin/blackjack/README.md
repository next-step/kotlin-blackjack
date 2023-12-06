# README

## Step2 기능 명세 
Top Down 형식으로 객체 역할과 책임 정의
- [x] `Nickname` 객체를 만든다.
  - [x] `Nickname`은 3자 이상 10자 이하의 문자열을 가진다.
- [x] `Suit` 객체를 만든다.
    - [x] `Suit`는 `Heart`, `Diamond`, `Spade`, `Clubs`를 가진다.
- [x] `Denomination` 객체를 만든다.
    - [x] `Denomination`는 `Ace`, `Two`, `Three`, `Four`, `Five`, `Six`, `Seven`, `Eight`, `Nine`, `Ten`, `Jack`, `Queen`, `King`를 가진다.
    - [x] `Denomination`는 `Ace`를 가지고 있다면 `Ace`의 숫자를 1 또는 11로 계산할 수 있다.
- [x] `Card` 객체를 만든다.
  - [x] `Card`는 `Suit`와 `Rank`를 가진다.
- [X] `Cards` 일급 컬렉션 객체를 만든다.
    - [x] `Cards`는 `List<Card>`를 가진다.
    - [x] `Card`가 추가되었을 경우 새로운 `Cards` 객체를 생성해 불변성을 보장한다.
    - [x] `Cards`는 `Card`를 뽑을 수 있다.
    - [x] `Cards`는 `Card`가 모두 소진되었을 경우 IllegalStateException을 발생시킨다.
- [x] `Deck` 객체를 만든다.
  - [x] `Deck`은 `Card`를 가진다.
  - [x] `Deck`은 `Card`를 뽑는다.
  - [x] `Deck`이 초기화되었을 경우 52 조합의 `Card`를 생성한다.
- [x] `PlayerDecision` 객체를 만든다.
  - [x] `PlayerDecision`은 `Hit`, `Stand`를 가진다.
- [x] `Player` 객체를 만든다.
  - [x] `Player`는 `Card`를 가진다.
  - [x] `Player`는 `Card`를 받는다.
  - [x] `Player`는 `Card`를 받을 때 `PlayerDecision`을 할 수 있다.
  - [x] `Player`는 `Bust` 상황에서 `Hit`에서 `Stand`로 `Decision`을 변경해야 한다. 
  - [x] `Player`는 가진 카드로부터 점수를 계산할 수 있다.  
- [x] `Players` 객체를 만든다.
  - [x] `Players`는 `List<Player>`를 가지는 일급 컬렉션이다.
  - [x] `Hit` 결정을 한 `Player`들을 반환한다.
- [x] BlackjackGame 컨트롤러 객체를 생성한다.
  - [x] `BlackjackGame`은 `Dealer`, `Players`를 가진다.
  - [x] 게임 초기화시 `Dealer`와 `Player`와 `Deck`을 세팅한다.
  - [x] 게임이 시작되면 `Player`에게 두장의 카드를 제공하고, 게임을 진행한다. 이후 `Player`별 결과를 반환한다.
- [x] 입출력 조건에 맞게 `InputView`, `OutputView`를 생성한다.

## Step3 기능 명세
- [x] `Dealer` 게임 참여자 생성
  - 요구사항
    - [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
    - [x] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
    - [x] Player와 중복된 로직을 제거한다. (인터페이스 활용 필)
- [x] 게임 승패 로직 구현
  - [x] 게임의 `State`를 나타내는 객체 생성 (필요하다면 다형성 활용할 것)
  - [x] 게임의 승패를 관리하는 `GameResult` 객체 생성
  - [x] 게임을 완료(모든 유저가 Hit이 아닌 경우)한 후 게임 참여자별 `승패`를 출력한다.
- [x] 입출력 조건에 맞게 `InputView`, `OutputView`를 생성한다.
