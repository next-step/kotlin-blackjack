package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard
import blackjack.participant.status.Blackjack
import blackjack.participant.status.Status

class BlackjackStrategy(
    private val scoreCalculator: ScoreCalculator,
) {
    var cards: List<BlackJackCard> = emptyList()
    lateinit var status: Status

    val isBust get() = resultScore() > BLACKJACK

    fun drawCard(cards: List<BlackJackCard>) {
        this.cards += cards
        if (cards.size == FIRST_TURN_DRAW && isBlackjack()) {
            status = Blackjack()
        }
    }

    fun resultScore(): Int {
        return scoreCalculator.calculateGameScore(cards)
    }

    private fun isBlackjack(): Boolean {
        return resultScore() == BLACKJACK
    }

    companion object {
        private const val BLACKJACK: Int = 21
        private const val FIRST_TURN_DRAW: Int = 2
    }
}