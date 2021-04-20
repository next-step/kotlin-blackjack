package blackjack.model.gamer

import blackjack.model.Bet
import blackjack.model.Rule
import blackjack.model.score.Score
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

class Dealer(cards: Cards, name: String = "딜러") : Gamer {
    private val gamer: Player = Player(cards, name, Bet.ZERO)

    override val cards: Cards
        get() = gamer.cards
    override val name: String
        get() = gamer.name
    override val bet: Bet
        get() = gamer.bet

    constructor(deck: Deck) : this(Cards.firstDraw(deck))

    fun drawIfNeeded(deck: Deck) {
        if (gamer.cards.getHighestScore() <= MINIMUM_SCORE) {
            gamer.draw(deck)
        }
    }

    fun didOneMoreDraw() = cards.size > Cards.INITIAL_DRAW_COUNT

    override fun keepDrawing(userResponse: Boolean, deck: Deck): Boolean = gamer.keepDrawing(userResponse, deck)

    override fun hasValidScore(rule: Rule): Boolean = gamer.hasValidScore(rule)

    override fun getScore(rule: Rule): Score = gamer.getScore(rule)

    companion object {
        val MINIMUM_SCORE = Score(16)
    }
}
