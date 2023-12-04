package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard

class BlackjackStrategy(
    private val scoreCalculator: ScoreCalculator,
) {
    var cards: List<BlackJackCard> = emptyList()

    val isBust get() = resultScore() > BUST

    fun drawCard(cards: List<BlackJackCard>) {
        this.cards += cards
    }

    fun resultScore(): Int {
        return scoreCalculator.calculateGameScore(cards)
    }

    companion object {
        private const val BUST: Int = 21
    }
}