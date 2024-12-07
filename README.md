# kotlin-blackjack

### 기능 요구사항
- 블랙잭 게임을 변형한 프로그램을 구현한다. 
- 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 
- 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 요구 사항 명세 정리
- [x] 카드 모양은 Spades, Clubs, Hearts, Diamonds 4가지로 이루어져있다
- [x] 카드는 1부터 10까지로 이루어져 있다
- [x] 숫자가 아닌 카드는 Jack, Queen, King으로 이루어져있으며 모두 숫자 10을 나타낸다
- [x] 모든 카드는 중복이 불가능 하도록 미리 생성하여 관리한다
- [ ] Ace(숫자1)는 1 또는 11로 계산할 수 있다
- [ ] 플레이어가 2명 미만일 경우 에러가 발생한다
- [ ] 플레이어는 본인이 원할 때 까지 카드를 계속 뽑을 수 있다


### 프로그래밍 요구 사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- **모든 엔티티를 작게 유지한다.**
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.
