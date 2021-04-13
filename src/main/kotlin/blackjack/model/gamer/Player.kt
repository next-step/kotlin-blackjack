package blackjack.model.gamer

import blackjack.model.Bet
import blackjack.model.Rule
import blackjack.model.score.Score
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

open class Player(cards: Cards, override val name: String, override val bet: Bet) : Gamer {

    constructor(deck: Deck, name: String, bet: Bet) : this(Cards.firstDraw(deck), name, bet)

    override var cards = cards
        protected set

    override fun hasValidScore(rule: Rule) = getScore(rule).isValid()

    override fun getScore(rule: Rule): Score {
        return rule.getScore(cards)
    }

    override fun keepDrawing(userResponse: Boolean, deck: Deck): Boolean {
        if (userResponse) {
            draw(deck)
        }
        return userResponse
    }

    fun draw(deck: Deck) {
        cards = cards.draw(deck)
    }
}
