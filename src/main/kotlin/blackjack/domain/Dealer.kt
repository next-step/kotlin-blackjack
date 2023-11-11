package blackjack.domain

class Dealer(cards: Cards = Cards()) : BlackJackPlayer(DEALER_NAME, cards) {

    override fun isHit(): Boolean {
        return cards.score() < Score(DEALER_HIT_SCORE)
    }

    companion object {
        private const val DEALER_HIT_SCORE = 17
        private const val DEALER_NAME = "딜러"
    }
}
