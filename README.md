# kotlin-blackjack

### 기능 요구 사항
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 프로그래밍 요구 사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외 
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다. 
- 모든 엔티티를 작게 유지한다. 
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다. 
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다. 
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

### 상세 구현 사항
[x] 게임 참여자의 문자열을 입력받는다. (쉼표 구분)
[x] 카드 종류에 대한 열거를 정의한다.
[x] 게임 참여자가 가지고 있는 카드의 종류를 저장하고 합계를 계산한다.
[x] 게임 참여자는 카드를 더 받을 것 인지 선택할 수 있다.
[x] 빈 문자열 또는 null을 입력할 경우 다시 입력하라는 메시지를 출력한다.
[x] 참여자 중 결과값이 21에 근접하게 도달한 사람이 있다면 즉시 게임이 종료된다.
[x] 합계가 21을 초과해도 즉시 게임이 종료된다.
[x] 한번 뽑은 카드는 뽑을 수 없다.
[x] 랜덤으로 카드를 배분하며, 최초에는 2장 먼저 배분한다.
[x] 결과를 출력한다.
[x] 딜러는 처음 받은 두 장의 합이 16이하면 1장을 받아야한다.
[x] 딜러가 21점을 초과하면 남은 플레어이 패에 상관없이 승리한다.
[x] 딜러나 플레이어중 21를 넘지 않으면서 21에 가까운 사람이 승리한다.
[x] 게임완료 후 플레이어별 승패를 출력한다.
