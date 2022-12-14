# kotlin-blackjack

## 블랙잭

### 기능 요구사항

- [ ] 플레이어를 생성한다
    - [x] 플레이어 이름을 입력받는다
    - [x] 플레이어의 이름은 쉼표를 기준으로 구분한다
    - [ ] 플레이어가 가진 카드 숫자를 합친다
    - [ ] King, Queen, Jack은 10으로 계산한다
    - [ ] Ace는 1 또는 11로 계산한다
    - [ ] 카드 합이 21을 넘을 경우 카드를 뽑지 못한다
- [ ] 카드를 생성한다
    - [ ] 카드는 슈트(Suit)와 끗수(Denomination)로 구성된다
    - [ ] 하나의 덱은 52개의 카드로 구성된다
- [ ] 카드를 지급한다
    - [ ] 랜덤으로 두개의 카드를 지급한다
    - [ ] 지급하고 카드 현황을 출력한다
    - [ ] 지급 후 덱에서 카드가 없어진다
    - [ ] 덱에 카드가 없으면 뽑을 수 없다
- [ ] 각 플레이어에게 카드를 추가로 지급할지 묻는다
    - [ ] y이면 카드를 추가로 지급한다
    - [ ] 플레이어가 n을 답할때까지 묻고 n을 답하면 카드 현황을 출력한다
- [ ] 게임 결과를 출력한다
