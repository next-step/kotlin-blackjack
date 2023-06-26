package domain.state

import domain.card.Cards
import java.math.BigDecimal
import java.math.RoundingMode

class Blackjack(cards: Cards, betAmount: Int = 0) : TerminationState(cards, betAmount) {

    override fun getRevenue(state: State): Int =
        if (state !is Blackjack) { calculateRevenue() } else { super.getRevenue(state) }

    private fun calculateRevenue(): Int = BigDecimal(getBetAmount())
        .multiply(BigDecimal(BLACKJACK_REVENUE))
        .setScale(0, RoundingMode.DOWN)
        .toInt()

    companion object {
        private const val BLACKJACK_REVENUE = "1.5"
    }
}
