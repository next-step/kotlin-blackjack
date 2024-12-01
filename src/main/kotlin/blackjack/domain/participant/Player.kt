package blackjack.domain.participant

import blackjack.domain.betting.Betting
import blackjack.domain.card.Cards
import blackjack.domain.score.Score
import blackjack.domain.score.Score.Companion.MAX_SCORE

class Player(
    val betting: Betting,
    name: String,
    cards: Cards = Cards(),
) : Participant(name, cards) {
    fun calculateRate(dealer: Dealer): Double {
        if(Score(dealer.cards.getScore()).isBust()) {
            return RATE_ONE
        }

        if(Score(cards.getScore()).isBust() || (cards.getScore() < dealer.cards.getScore())) {
            return NEGATIVE_RATE_ONE
        }

        if(cards.getCards().size == 2 && cards.getScore() == MAX_SCORE){
            return RATE_ONE_POINTS_FIVE
        }

        if(cards.getScore() == MAX_SCORE && dealer.cards.getScore() == MAX_SCORE) {
            return RATE_ONE
        }

        return RATE_ONE
    }

    override fun canReceive(): Boolean {
        return !Score(cards.getScore()).isBust()
    }

    companion object {
        private const val RATE_ONE = 1.0
        private const val RATE_ONE_POINTS_FIVE = 1.5
        private const val NEGATIVE_RATE_ONE = -1.0
    }
}
