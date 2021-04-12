package blackjack.model.gamer

import blackjack.model.Judge
import blackjack.model.Rule
import blackjack.model.score.Score
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

open class Player(cards: Cards, override val name: String) : Gamer {

    constructor(deck: Deck, name: String) : this(Cards.firstDraw(deck), name)

    override var cards = cards
        protected set

    override fun isWin(opponent: Gamer, rule: Rule): Boolean {
        return Judge.isWin(getScore(rule), opponent.getScore(rule))
    }

    override fun isLose(opponent: Gamer, rule: Rule): Boolean {
        return !isWin(opponent, rule)
    }

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
