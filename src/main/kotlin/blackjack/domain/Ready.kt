package blackjack.domain

class Ready(
    override val hand: Hand,
) : Playing() {
    init {
        require(hand.size >= Hand.INITIAL_HAND_SIZE) { "카드 두 장 이상이어야 합니다." }
    }

    override fun drawFrom(deck: Deck): State {
        hand.drawFrom(deck)
        return when {
            hand.isBusted() -> Busted(hand)
            hand.isTwentyOne -> Stand(hand)
            else -> Ready(hand)
        }
    }

    override fun stand(): State = Stand(hand)
}
