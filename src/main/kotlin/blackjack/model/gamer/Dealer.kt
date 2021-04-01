package blackjack.model.gamer

import blackjack.model.score.Score
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

class Dealer(cards: Cards, deck: Deck, name: String = "딜러", private val gamer: Player = Player(cards, name)) : Gamer by gamer {
    constructor(deck: Deck) : this(Cards.firstDraw(deck), deck)

    init {
        checkNeedToDraw(deck)
    }

    private fun checkNeedToDraw(deck: Deck) {
        if (gamer.cards.getHighestScore() <= MINIMUM_SCORE) {
            gamer.draw(deck)
        }
    }

    fun didOneMoreDraw() = gamer.cards.size > Cards.INITIAL_DRAW_COUNT

    companion object {
        val MINIMUM_SCORE = Score(16)
    }
}
