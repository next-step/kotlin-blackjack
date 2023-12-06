package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard

class Dealer(
    scoreCalculator: ScoreCalculator
)  {
    private val blackjackStrategy: BlackjackStrategy = BlackjackStrategy(scoreCalculator)
    val bettingAmount: BettingAmount = BettingAmount(0)

    val cards get() = blackjackStrategy.cards

    val isBust get() = blackjackStrategy.isBust

    val status get() = blackjackStrategy.status

    fun drawCard(cards: List<BlackJackCard>) {
        blackjackStrategy.drawCard(cards)
    }

    fun resultScore(): Int {
        return blackjackStrategy.resultScore()
    }

    fun shouldDraw(): Boolean {
        return blackjackStrategy.resultScore() < MAX_DRAW_SCORE
    }

    companion object {
        private const val MAX_DRAW_SCORE: Int = 17
    }
}
