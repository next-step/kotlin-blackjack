package blackjack.domain

class Dealer(
    hand: Hand = Hand()
) : User(hand) {
    override fun hit(card: Card) {
        require(hand.getSum() <= 16) { "딜러는 17점 이상이면 카드를 추가로 받을 수 없습니다." }
        hand.receive(card)

        state = if (hand.getSum() > 21) {
            State.BUST
        } else if (hand.getSum() > 16) {
            State.STAND
        } else {
            State.HIT
        }
    }
}
