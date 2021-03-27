package blackjack.model.player

import blackjack.model.Rule
import blackjack.model.Score
import blackjack.model.trump.Cards

open class Player(cards: Cards, val name: String) {
    var cards = cards
        protected set

    fun getScore(rule: Rule): Score {
        return rule.getScore(cards)
    }

    fun keepDrawing(userResponse: String): Boolean {
        if (userResponse == "y") {
            draw()
            return true
        }
        return false
    }

    private fun draw() {
        cards = cards.draw()
    }
}
