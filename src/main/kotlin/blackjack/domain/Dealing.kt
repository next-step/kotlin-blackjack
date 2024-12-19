package blackjack.domain

class Dealing(
    override val hand: Hand = Hand(),
) : Playing() {
    init {
        require(hand.size <= Hand.INITIAL_HAND_SIZE) { "카드 숫자가 두 장을 초과할 수 없습니다." }
    }

    override fun drawFrom(deck: Deck): State {
        hand.drawFrom(deck)
        return when {
            hand.size < Hand.INITIAL_HAND_SIZE -> Dealing(hand)
            hand.isBlackjack() -> Blackjack(hand)
            else -> Ready(hand)
        }
    }

    override fun stand(): State = throw IllegalStateException("햔재 상태에서는 스탠드를 할 수 없습니다.")
}
