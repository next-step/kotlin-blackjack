package blackjack.domain

class Dealer(
    cards: Cards,
) : Participant(DEALER_NAME, cards) {
    override fun openedCards(): Cards = Cards(cards.take(DEALER_OPEN_CARD_COUNT))

    fun shouldHit(): Boolean {
        return calculateScore() <= MAX_SCORE_DEALER_SHOULD_HIT
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_OPEN_CARD_COUNT = 1
        private const val MAX_SCORE_DEALER_SHOULD_HIT = 16
    }
}
