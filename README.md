# kotlin-blackjack

## 블랙잭

### 기능 목록
- CardNumber
  - [x] Enum 으로 Ace, Two ~ Ten, Jack, Queen, King 이 있다.
  - [x] 각각 값은 대응되는 숫자를 가지며 A는 1, 11 두 숫자에 대응, K, Q, J는 10이다.
- Suit
  - [x] Enum 으로 Spade, Heart, Diamond, Club이 있다.
- Card
  - [x] 카드는 CardNumber와 Suit 을 속성으로 갖는다.
- Cards
  - [x] 카드들을 속성으로 갖는다.
  - [x] 동일한 카드를 한장 이상 가질 수 없다.
  - [x] 지급받은 카드들의 숫자 합을 반환한다. (A는 1, 11로 대응된다.)
- Score
  - [x] 점수를 의미하는 객체로 Value class 이다.
  - [x] 21점을 넘었는지 판단하는 판단한다.
  - [x] 음수를 가질 수 없다.
- Player
  - [x] 이름과 Cards 를 속성으로 갖는다.
  - [x] 지급받은 카드들의 숫자 합을 반환한다. (A는 1, 11로 대응된다.)
  - [x] 게임 종료 여부를 반환한다.
  - [x] 해당 플레이어의 게임을 종료한다.
- Players
  - [x] Player들을 속성으로 갖는다.
  - [x] 아직 종료되지 않은 유저 목록을 반환한다.
- BlackjackGame
  - [x] 모든 카드가 있는 상태로 초기화된 Cards 를 필드로 갖는다.
  - [x] Players들을 생성자로 받는다.
  - [x] Players에게 초기 카드를 발급한다.
  - [x] 게임이 종료되었는지를 반환한다.
  - [x] 턴을 진행한다. (플레이어 한명에게 히트, 스테이 여부확인해 진행) 
- BlackjackController
  - [x] InputView 로부터 게임에 참여할 사람 이름을 입력받는다.
  - [x] 플레이어를 초기화하고 상태를 출력한다.
  - [x] 아직 종료되지 않은 플레이어를 받아 카드 지급 여부를 확인하고 필요시 지급한다.
  - [x] 추가지급을 하지 않는 플레이어는 게임을 종료한다.
  - [x] 모든 플레이어를 출력한다.
- Dealer
  - [x] 플레이어를 상속받는 딜러가 존재한다.
- PlayerResult
  - [x] 플레이어와 승, 패 여부를 갖는다. 
- DealerResult
  - [x] 플레이어와 승, 패 횟수를 갖는다.
- Results
  - [ ] 딜러, 플레이어의 결과를 생성한다.
  - [ ] 생성자 인자로 딜러와 플레이어를 받아 승, 패 결정 후 PlayerResult, DealerResult를 만든다.
