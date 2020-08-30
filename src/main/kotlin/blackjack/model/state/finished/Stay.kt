package blackjack.model.state.finished

import blackjack.model.card.Cards
import blackjack.model.state.State

data class Stay(override val cards: Cards) : Finished() {
    override val earningsRate: Double = 1.0

    override fun profit(dealerState: State, betMoney: Int): Double {
        val playerScore = cards.score()
        val dealerScore = dealerState.cards.score()
        return when {
            dealerState.cards.isBust() -> earningsRate * betMoney
            playerScore > dealerScore -> earningsRate * betMoney
            playerScore == dealerScore -> 0.0
            else -> -earningsRate * betMoney
        }
    }
}
