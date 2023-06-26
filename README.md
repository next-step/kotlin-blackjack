# kotlin-blackjack

## 기능 구현 목록
- 게임에 참여할 사람의 이름을 입력받는다.
- 이름을 쉼표 기준으로 분리하여 각 이름을 갖는 Player를 생성한다.
  - player는 이름과 카드, 점수를 갖는다.
- 각 Player에게 카드를 2장씩 분배한다.
- 각 Player가 받은 카드를 쉼표로 분리하여 화면에 출력한다. 
  - pobi카드: 2하트, 8스페이드 
  - jason카드: 7클로버, K스페이드
- 각 Player에게 카드를 한장씩 더 받을지 물어본다.
  - y를 입력받으면 카드를 더 주고 n을 입력받으면 주지 않는다.
  - 해당 player의 점수가 21점을 넘지 않는다면 n을 입력받을 때까지 반복한다.
  - 카드를 더 줄 때마다 해당 player가 갖고있는 카드를 출력한다. 
- 모든 플레이어가 카드를 더 이상 받지 않으면 결과를 출력한다.
  - 결과는 플레이어별로 갖고 있는 카드의 목록과 점수를 출력한다.

## 테스트 구현 목록
- [x] Test 1 : Player 객체에 이름을 부여할 수 있다.
  - 먼저 테스트를 작성 후 이름만 받아 생성되는 Player 클래스 작성
- [x] Test 2 : 쉼표 기준으로 분리된 이름을 받아 Player들을 생성한다.
  - Players 클래스를 작성하여 전체 플레이어를 담도록 함
