package blackjack.model.player

import blackjack.model.Judge
import blackjack.model.Rule
import blackjack.model.score.Score
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

open class Player(cards: Cards, val name: String) {

    constructor(deck: Deck, name: String) : this(Cards.firstDraw(deck), name)

    var cards = cards
        protected set

    fun isWin(opponent: Player, rule: Rule): Boolean {
        return Judge.isWin(getScore(rule), opponent.getScore(rule))
    }

    fun isLose(opponent: Player, rule: Rule): Boolean {
        return !isWin(opponent, rule)
    }

    fun hasValidScore(rule: Rule) = getScore(rule).isValid()

    private fun getScore(rule: Rule): Score {
        return rule.getScore(cards)
    }

    fun keepDrawing(userResponse: String, deck: Deck): Boolean {
        if (userResponse == "y") {
            draw(deck)
            return true
        }
        return false
    }

    private fun draw(deck: Deck) {
        cards = cards.draw(deck)
    }
}
