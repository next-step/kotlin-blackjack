package blackjack.model.player

import blackjack.model.Judge
import blackjack.model.Rule
import blackjack.model.Score
import blackjack.model.trump.Cards

open class Player(cards: Cards, val name: String) {
    var cards = cards
        protected set

    fun isWin(opponent: Player, rule: Rule): Boolean {
        return Judge.isWin(getScore(rule), opponent.getScore(rule))
    }

    fun isLose(opponent: Player, rule: Rule): Boolean {
        return !isWin(opponent, rule)
    }

    private fun getScore(rule: Rule): Score {
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
