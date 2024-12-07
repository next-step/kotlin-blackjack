# kotlin-blackjack

## Step2 - 블랙잭

### Step1 - 리뷰반영사항
- [x] SkillsBuilder::가변성 중첩 제거
- [x] DslTest::테스트 클래스와 일반 클래스 분리

### 기능 구현사항
- [x] 카드는 랭크와 문양으로 이루어진다.
- [x] 카드의 랭크는 2~10,J,Q,K,A 이다.
- [x] 랭크가 숫자이면 점수는 숫자의 값으로 계산된다.
- [x] 랭크가 J,Q,K 이면 점수는 10점으로 계산된다.
- [x] 랭크가 에이스이면 기본점수는 11점이다.
- [x] 카드들의 점수합이 21을 초과할 경우 에이스는 1점으로 보정된다.
- [x] 카드목록의 점수는 카드의 점수의 합이다.
- [x] 카드의 점수합이 21점 미만일 경우 카드를 더 받을 수 있다.
- [x] 카드의 점수합이 21점 이상할 경우 카드를 더 받을 수 없다.
- [x] 유저는 카드를 더 받는다.
- [x] 덱에서 카드를 하나 꺼낸다.
- [x] 유저는 카드 목록을 보유한다.
- [x] 카드목록에서 점수를 계산한다.
- [x] 카드를 덱에서 하나 뽑는다.

## Step3 - 블랙잭(딜러)

### Step2 - 리뷰반영사항
- [x] BlackJackGame::game table 이 users 와 deck 을 상태로서 관리하도록 수정
- [x] BlackJackGame::start 메서드 분리
- [x] BlackJackGame::유저목록을 받아 card 출력하도록 수정
- [x] BlackJackGame::카드 히트 여부확인시 출력과 입력을 통합
- [x] BlackJackGame::while 문 내부 if 절을 while 조건식으로 통합
- [x] BlackJackGame::게임진행책임 분리
- [ ] BlackJackGame::receive 를 hit 로 메서드명 수정
- [ ] Rank::enum 클래스로 수정