# kotlin-blackjack

## 1단계 - 코틀린 DSL
- [x] Person - name DSL 생성
- [x] Person - company DSL 생성
- [x] Person - skills DSL 생성
- [x] Person - languages DSL 생성
  - [x] languages - Subject, Level 정보 추가
- [x] Skill - hard, soft DSL 생성

<br>

## 2단계 - 블랙잭
- [ ] 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 승리한다.
  - [ ] 21을 가진 사람이 승리한다.
  - [ ] 21을 가진 사람이 없을 경우 21에 가장 가까운 숫자가 승리한다.
  - [ ] 21을 초과하면 버스트된다. (무조건 패배)
- [ ] 게임을 시작하면 모든 플레이어는 2장의 카드를 지급 받는다.
  - [ ] 카드의 합이 21을 넘지 않을 경우 얼마든지 카드를 계속 뽑을 수 있다.
- [x] 카드의 숫자는 카드 숫자를 기본으로 한다.
  - [x] King, Queen, Jack은 10이다.
  - [ ] Ace는 1 또는 11이 될 수 있다.
- [ ] 게임에 참여할 사람의 이름을 입력할 수 있다.
