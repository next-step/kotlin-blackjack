package blackjack.model.gamer

import blackjack.model.BetMoney
import blackjack.model.score.Score
import blackjack.model.state.State
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

interface Gamer {
    val isBlackJack: Boolean
    val isBust: Boolean
    val cards: Cards
    val state: State
    val name: String
    val betMoney: BetMoney

    fun keepDrawing(userResponse: Boolean, deck: Deck): Boolean
    fun getScore(): Score
    fun calculateRevenue(): BetMoney
    fun draw(deck: Deck)
}
