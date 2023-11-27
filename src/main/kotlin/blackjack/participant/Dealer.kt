package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard

class Dealer(
    private val scoreCalculator: ScoreCalculator
) {
    val name: String = "딜러"
    private val cards: MutableList<BlackJackCard> = mutableListOf()

    fun drawCard(cards: List<BlackJackCard>) {
        this.cards.addAll(cards)
    }

    fun shouldDraw(): Boolean {
        return resultScore() <= DEALER_SHOULD_DRAW_SCORE
    }

    fun resultScore(): Int {
        return scoreCalculator.calculateGameScore(cards)
    }

    companion object {
        private const val DEALER_SHOULD_DRAW_SCORE: Int = 16
    }
}
