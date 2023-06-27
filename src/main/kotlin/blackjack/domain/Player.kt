package blackjack.domain

import java.math.BigDecimal

class Player(
    name: String,
    val betAmount: BigDecimal,
    state: State,
) : Participant(name, state) {
    override fun openedCards(): Cards = state.cards

    fun calculateProfit(dealer: Dealer): Profit {
        if (state is Burst) {
            return Profit(-betAmount)
        }

        val dealerScore: Score = dealer.calculateScore()
        if (dealer.state is Burst) {
            return Profit(betAmount)
        }

        val score: Score = calculateScore()
        if (score > dealerScore) {
            return state.calculateProfit(betAmount)
        }

        if (score == dealerScore) {
            return Profit.ZERO
        }

        return Profit(-betAmount)
    }

    companion object {
        fun of(name: String, betAmount: BigDecimal, cards: Cards) = Player(
            name = name,
            betAmount = betAmount,
            state = Running(cards),
        )
    }
}
