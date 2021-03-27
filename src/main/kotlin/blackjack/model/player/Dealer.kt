package blackjack.model.player

import blackjack.model.Score
import blackjack.model.trump.Cards

class Dealer(cards: Cards, name: String = "딜러") : Player(cards, name) {
    init {
        checkNeedToDraw()
    }

    private fun checkNeedToDraw() {
        if (super.cards.getHighestScore() <= MINIMUM_SCORE) {
            super.cards = super.cards.draw()
        }
    }

    fun isDraw() = this.cards.size > Cards.INITIAL_DRAW_COUNT

    companion object {
        val MINIMUM_SCORE = Score(16)
    }
}
