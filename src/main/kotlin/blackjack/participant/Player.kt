package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard

class Player(
    val name: String,
    private val scoreCalculator: ScoreCalculator
) {
    var cards: List<BlackJackCard> = emptyList()

    fun drawCard(cards: List<BlackJackCard>) {
        this.cards += cards
    }

    fun shouldDraw(): Boolean {
        return resultScore() <= 21
    }

    fun resultScore(): Int {
        return scoreCalculator.calculateGameScore(cards)
    }
}
