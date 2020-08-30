package blackjack.model.profit

import blackjack.model.participant.Dealer.Companion.DEALER_NAME

data class Profits(private val _profits: List<Profit>) {
    val profits: List<Profit> = _profits.deepCopy()

    fun dealerProfit(): Profit = Profit(DEALER_NAME, -sumAll())

    private fun sumAll() = profits.sumBy { it.profit }

    private fun List<Profit>.deepCopy(): List<Profit> = this.map { it.copy() }

    override fun toString(): String = profits.joinToString("\n") { "${it.participantName}: ${it.profit}" }
}
