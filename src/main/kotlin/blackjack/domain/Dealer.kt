package blackjack.domain

class Dealer(
    cards: Cards,
    burst: Boolean = false,
) : Participant(DEALER_NAME, cards, burst) {
    override fun openedCards(): Cards = Cards(cards.take(DEALER_OPEN_CARD_COUNT))

    fun shouldHit(): Boolean {
        return calculateScore() <= MAX_SCORE_DEALER_SHOULD_HIT
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_OPEN_CARD_COUNT = 1
        private val MAX_SCORE_DEALER_SHOULD_HIT = Score(16)
    }
}
