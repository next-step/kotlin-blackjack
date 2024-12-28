package blackjack.domain.player

class Player(
    private val name: PlayerName,
    private val betAmount: BetAmount,
) : Participant() {
    override fun toString(): String {
        return name.toString()
    }

    fun calculateProfit(dealerState: DealerState): Double {
        return state.profit(betAmount.value, dealerState)
    }

    companion object {
        fun of(
            name: String,
            betAmount: Int,
        ): Player {
            return Player(PlayerName.from(name), BetAmount.from(betAmount))
        }
    }
}
