package blackjack.model.player

import blackjack.model.score.Score
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

class Dealer(cards: Cards, deck: Deck, name: String = "딜러") : Player(cards, name) {

    constructor(deck: Deck) : this(Cards.firstDraw(deck), deck)

    init {
        checkNeedToDraw(deck)
    }

    private fun checkNeedToDraw(deck: Deck) {
        if (super.cards.getHighestScore() <= MINIMUM_SCORE) {
            super.cards = super.cards.draw(deck)
        }
    }

    fun didOneMoreDraw() = this.cards.size > Cards.INITIAL_DRAW_COUNT

    companion object {
        val MINIMUM_SCORE = Score(16)
    }
}
