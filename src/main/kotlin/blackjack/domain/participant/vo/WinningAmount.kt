package blackjack.domain.participant.vo

import blackjack.domain.Amount

data class WinningAmount(
    val amount: Amount
) {
    companion object {
        fun of(betAmount: BetAmount): WinningAmount = WinningAmount(betAmount.amount)
    }
}
