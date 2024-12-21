package blackjack.domain

class Dealer(
    state: State = Dealing(DealerHand()),
) {
    var state: State = state
        private set
    val hand: DealerHand get() = state.hand as DealerHand
    val value: Int get() = state.hand.value()
    val isBusted: Boolean get() = state.hand.isBusted()
    val isBlackjack: Boolean get() = state.hand.isBlackjack()

    fun drawFrom(deck: Deck) {
        state = state.drawFrom(deck)
    }

    fun flipHoleCardUp() {
        val hand = state.hand
        check(hand is DealerHand)
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
