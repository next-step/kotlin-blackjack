package blackjack.domain

class Dealer(
    hand: Hand = Hand()
) : User(hand) {
    override fun hit(card: Card) {
        require(hand.getSum() <= 16) { "딜러는 17점 이상이면 카드를 추가로 받을 수 없습니다." }
        hand.receive(card)
        state = State.HIT
    }

    override fun canHit(): Boolean {
        return hand.getSum() <= 16
    }
}
