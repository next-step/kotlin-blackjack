package blackjack.model.gamer

import blackjack.model.Bet
import blackjack.model.Rule
import blackjack.model.score.Score
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

interface Gamer {
    val cards: Cards
    val name: String
    val bet: Bet

    fun keepDrawing(userResponse: Boolean, deck: Deck): Boolean
    fun hasValidScore(rule: Rule): Boolean
    fun getScore(rule: Rule): Score
}
