# kotlin-blackjack

## 블랙잭 - 기능구현 목록표

- [x] 플레이어의 이름을 입력 받는기능
  - [x] 이름이 공백이면 예외를 발생시킨다. 
  - [x] 이름이 중복되면 예외를 발생시킨다.
- [x] 게임에 필요한 카드 뭉치들을 가지는 덱을 생성하는 기능
- [x] 플레이어들에게 2장 씩 카드를 나눠주는 기능
- [x] 플레이어들이 처음에 받은 카드 2장을 출력하는 기능
- [x] 카드들의 합을 구하는 기능
- [x] 자신의 턴인 플레이어를 찾는 기능
- [x] 플레이어들이 카드를 한 장씩 더 받을지 입력 받는다.
  - [X] 플레이어가 카드를 더 받을 수 있는지 확인한다.
- [x] 플레이어가 보유한 카드와 점수를 출력한다.
- [x] 참가자에 딜러를 추가한다.
  - [x] 딜러의 참가자 이름은 '딜러'이다. 
  - [x] 딜러가 카드를 더 받을 수 있는지 확인하여 카드를 더 받거나 받지않는다. (16점 이하인 경우 더 받는다)
  - [x] 첫 턴에 딜러의 카드는 한장만 오픈한다. 
- [x] 딜러와 플레이어 간의 점수를 비교하여 게임 승패 결과를 확인하는 기능 
- [x] 배팅 금액을 입력받는다. 
- [x] 승리 시 배팅금액에 따라 수익을 얻는 기능
  - [x] 승리자가 `블랙잭`인경우 수익금은 1.5배 얻는다.  
  - [x] 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
