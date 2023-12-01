# kotlin-blackjack

## step2
- 초기 설계

    <img src="./step2_design.png" width="300" alt="">


- [x] Player는 이름을 가진다.
- [x] Deck에서 카드를 원하는 개수만큼 뽑는다.
- [x] Deck보다 많은 수의 카드를 뽑으려고 하면 에러가 발생한다.
- [x] ACE는 1점 혹은 11점으로 계산되고, JQK는 10점으로 계산된다. 그 외는 자신의 숫자대로 계산된다.
- [x] Player는 Deck에서 카드를 뽑는다.
- [x] Player는 점수가 21점을 초과하면 카드를 뽑을 수 없다.
- [x] Player는 카드 뽑기를 멈출 수 있다.
- [x] Player의 점수 총합을 출력한다.


## step3
- [x] Dealer는 2장의 카드를 받고 시작한다. (Player와 동일)
- [x] Dealer는 16점 이하이면 Hit 상태, 이상이면 Stay, 21이면 Blackjack, 21 초과면 Bust 상태이다
- [x] ResultItem은 승부의 결과인 승/무/패를 가진다.
- [ ] Dealer는 Player와 승부 결과를 반환한다.
- [x] ResultItem은 상대방의 결과를 계산할 수 있다.
- [ ] Dealer가 21점을 초과하면 Player는 전원 승리한다.
- [ ] Player와 Dealer는 승부의 결과를 가진다.
