# kotlin-blackjack
## 도메인 구성
### 1. 블랙잭 카드
- 카드 숫자 (A,2~10,J,Q,K) 
- 참고) 카드 숫자별 점수처리
  - A = 1점 or 11점
  - 2~10 = 해당 숫자 점수
  - J = 10점
  - Q = 10점
  - K = 10점

### 2. 플레이어
- 이름
- 카드 리스트

### 3. 딜러
- 카드 리스트

### 4. 게임 유틸 서비스
- 카드 발급 (랜덤하게 발급) : cardDeckPublisher.drawCard() { .. }
- 카드 점수 계산 : cardSumCalculator.calc(..)
- 승자 판단 : (예시) winnerChecker.check(..)
- 게임 진행 로직 

## Input 처리
- 플레이어 이름 입력
- 카드 추가 발급 요청 (Y/N)

## Result 처리
- 플레이어와 딜러의 카드 및 합계 출력
- 최종 승패 출력