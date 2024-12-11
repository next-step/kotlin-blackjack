# kotlin-blackjack

## step-1
- 코틀린 DSL 실습

## step-2

### 요구 사항 정리
1. 게임에 참여할 사람의 이름을 입력받는다.
2. 게임 시작 시 플레이어들에게 두 장의 카드를 지급받는다.
   1. 숫자 카드는 숫자 그대로 계산하고, Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
3. 플레이어는 카드 총 합이 21에 가깝도록 카드를 한 장 씩 더 뽑을 수 있고 뽑기 중단 시 결과를 출력한다.

### Feedback
1. BlackJackApplication 도메인 분리
2. PlayerDeckCard - cards, aceCards 개선
3. PlayerCardDeck - 17 이상이면 추가로 받지 못하는 로직 추가
4. aceCards만 4개 넣은 경우 테스트
5. Player 예외 검증 로직 추가
6. 블랙잭 로직 수정