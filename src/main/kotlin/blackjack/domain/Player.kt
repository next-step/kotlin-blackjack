package blackjack.domain

import java.math.BigDecimal

class Player(
    name: String,
    val betAmount: BigDecimal,
    state: State,
) : Participant(name, state) {
    override fun openedCards(): Cards = state.cards

    fun calculateProfit(dealer: Dealer): Profit {
        val gameResult = state.gameResult(dealer.state)
        return gameResult.calculateProfit(betAmount, state.profitMultiple())
    }

    companion object {
        fun of(name: String, betAmount: BigDecimal, cards: Cards) = Player(
            name = name,
            betAmount = betAmount,
            state = State.of(cards),
        )
    }
}
