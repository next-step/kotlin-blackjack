package blackjack.domain.card.state

import blackjack.domain.card.Cards
import blackjack.domain.card.Score

class BlackJack(cards: Cards) : Finished(cards) {

    override val earningRate: Double = 1.5

    init {
        require(cards.score == SCORE) { "점수가 21점이 아니다" }
    }

    override fun isBlackJack() = true

    companion object {
        val SCORE = Score.of(21)
    }
}
