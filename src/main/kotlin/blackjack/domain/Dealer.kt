package blackjack.domain

class Dealer(
    val hand: DealerHand = DealerHand(),
) {
    val value: Int
        get() = hand.value()
    val isBusted: Boolean
        get() = hand.isBusted()
    val isBlackjack: Boolean
        get() = hand.isBlackjack()

    fun drawFrom(deck: Deck) {
        hand.drawFrom(deck)
    }

    fun flipHoleCardUp() {
        hand.flipHoleCardUp()
    }

    fun mustDrawCard(): Boolean = value <= MAX_MUST_DRAW_VALUE

    fun takeAction(deck: Deck) {
        while (mustDrawCard()) {
            drawFrom(deck)
        }
    }

    companion object {
        private const val MAX_MUST_DRAW_VALUE = 16
    }
}
