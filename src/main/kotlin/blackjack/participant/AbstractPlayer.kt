package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard

abstract class AbstractPlayer(
    private val scoreCalculator: ScoreCalculator,
    val name: String
) {
    var cards: List<BlackJackCard> = emptyList()
    val isBust get() = resultScore() > 21

    fun drawCard(cards: List<BlackJackCard>) {
        this.cards += cards
    }

    fun resultScore(): Int {
        return scoreCalculator.calculateGameScore(cards)
    }

    abstract fun isDealer(): Boolean

    abstract fun shouldDraw(): Boolean
}
