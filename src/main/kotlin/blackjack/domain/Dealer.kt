package blackjack.domain

class Dealer(override val cards: Cards = Cards()) : BlackJackPlayer() {

    override val name: String = DEALER_NAME

    init {
        if (isBlackJack()) {
            blackjack()
        }
    }

    override fun firstOpenCards(): Cards {
        return cards.take(FIRST_OPEN_COUNT)
    }

    override fun isHit(): Boolean {
        return cards.score() < Score(DEALER_HIT_SCORE)
    }

    companion object {
        private const val DEALER_HIT_SCORE = 17
        private const val DEALER_NAME = "딜러"
        private const val FIRST_OPEN_COUNT = 1
    }
}
