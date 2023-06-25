package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.score.Score

class Dealer(cards: Cards) : AbstractPlayer(NAME, cards) {
    override fun isEligibleToHit(): Boolean {
        return _cards.size == ELIGIBLE_CARD_SIZE && score <= ELIGIBLE_HIT_MAX_SCORE
    }

    companion object {
        private const val ELIGIBLE_CARD_SIZE = 2
        private val ELIGIBLE_HIT_MAX_SCORE = Score(16)
        const val NAME = "딜러"
    }
}
