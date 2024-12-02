package blackjack.domain.participant

import blackjack.domain.betting.Betting
import blackjack.domain.card.Cards

class Player(
    val betting: Betting,
    name: String,
    cards: Cards = Cards(),
) : Participant(name, cards) {
    fun calculateRate(dealer: Dealer): Double {
        if (dealer.cards.getScore().isBust()) {
            return RATE_ONE
        }

        if (cards.getScore().isBust() || (cards.getScore() < dealer.cards.getScore())) {
            return NEGATIVE_RATE_ONE
        }

        if (cards.getCards().size == START_PHASE_CARDS_SIZE && cards.getScore().isBlackJack()) {
            return RATE_ONE_POINTS_FIVE
        }

        if (cards.getScore().isBlackJack() && dealer.cards.getScore().isBlackJack()) {
            return RATE_ONE
        }

        return RATE_ONE
    }

    override fun canReceive(): Boolean {
        return !cards.getScore().isBust()
    }

    companion object {
        private const val RATE_ONE = 1.0
        private const val RATE_ONE_POINTS_FIVE = 1.5
        private const val NEGATIVE_RATE_ONE = -1.0
        private const val START_PHASE_CARDS_SIZE = 2
    }
}
