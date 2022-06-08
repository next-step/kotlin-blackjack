# kotlin-blackjack

## 기능 요구사항
- 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

## 프로그래밍 요구사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

### todo list 1
- [x] 게임에 참여할 사람을 입력받는다
- [x] 카드 객체를 만든다 숫자, 모양을 가질 수 있을듯.
- [x] 둘다 n 할때까지 카드를 더 받을지 묻는다.
- [x] y라고 답한경우 카드 한장 더준다
- [x] 플레이어 객체를 만든다. (카드 리스트를 가질 수 있을듯)
- [x] 둘다 n이라고 하면 카드리스트, 합계를 출력한다. (모양은 상관없네..)
- [x] 카드는 캐싱해서 사용할지? 
- [x] 게임을 시작하면 2장의 카드를 준다
- [x] 에이스는 1또는 11로 계산할 수 있고, K Q J 는 10으로 계산한다.
- [x] 딜러가 중복없는 모든 카드를 내놓는지 테스트
- [x] 플레이어가 가진 카드의 합계가 맞는지 테스트
- [x] y, n 이 아닌게 들어오면 예외발생하는지 테스트

### todo list 2
- [x] 들여쓰기 2넘지 않게 제한
- [x] 블랙잭 게임 추상화하여 이식하기 쉽게 변경
- [x] data class 활용
- [x] 메서드 길이 15라인
- [x] Card클래스의 toString 사용성 생각해보기
- [x] 딜러의 카드뭉치를 외부에서 주입
- [x] 뷰/도메인 패키지 분리
- [x] 카드뭉치 생성하는 클래스가 중복없는게 딜러가 뽑는게 중복없는 테스트보다 나을듯
- [x] 카드 합계계산하는 케이스가 ACE계산방식에 따라 다른 테스트 케이스

### todo list 3
- [ ] ACE 계산 다시 생각
- [ ] 카드 계산 형태 변경
- [ ] checkResultCandidate네이밍 변경
- [ ] YES, NO 를 객체로 표현하고 내부에 검증
- [ ] 생성자 파라미터 개수 3개이상일때 컨벤션 고려
- [ ] offerInitialCards, proceedGame 등 연관된 메서드끼리 가까이 위치
