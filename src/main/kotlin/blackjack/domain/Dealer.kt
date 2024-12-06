package blackjack.domain

class Dealer(
    val hand: DealerHand = DealerHand(),
) {
    val value: Int
        get() = hand.value()

    fun initialDrawFrom(deck: Deck) {
        hand.drawFrom(deck)
    }

    fun flipHoleCardUp() {
        hand.flipHoleCardUp()
    }

    fun mustDrawCard(): Boolean = value <= MAX_MUST_DRAW_VALUE

    companion object {
        private const val MAX_MUST_DRAW_VALUE = 16
    }
}
