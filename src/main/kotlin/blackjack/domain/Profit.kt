package blackjack.domain

class Profit private constructor(private val amount: Int) {

    override fun toString(): String = amount.toString()

    fun getAmount(): Int = amount

    companion object {
        private const val RATE_BLACK_JACK = 1.5
        fun of(bettingAmount: BettingAmount, isBlackjack: Boolean): Profit {
            return if (isBlackjack) {
                of((bettingAmount.value * RATE_BLACK_JACK).toInt())
            } else {
                of(bettingAmount)
            }
        }

        fun of(bettingAmount: Int): Profit = Profit((bettingAmount))
        fun of(bettingAmount: BettingAmount): Profit = Profit(bettingAmount.value)
        fun ofLose(bettingAmount: BettingAmount): Profit = Profit(bettingAmount.value.unaryMinus())
        fun ofDealer(playersResult: List<PlayerResult>): Profit {
            return Profit.of(playersResult.sumOf { it.getProfit() }.times(-1))
        }
    }
}
