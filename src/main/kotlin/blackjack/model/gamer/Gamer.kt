package blackjack.model.gamer

import blackjack.model.Rule
import blackjack.model.score.Score
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

interface Gamer {
    val cards: Cards
    val name: String

    fun isWin(opponent: Gamer, rule: Rule): Boolean
    fun isLose(opponent: Gamer, rule: Rule): Boolean
    fun keepDrawing(userResponse: String, deck: Deck): Boolean
    fun hasValidScore(rule: Rule): Boolean
    fun getScore(rule: Rule): Score
}
