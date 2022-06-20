package blackjack.domain.bet

import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState

object ProfitCalculator {
    fun calculate(player: PlayerResult, dealer: PlayerResult): Profit = when (player.finalState) {
        PlayerState.Blackjack -> when (dealer.finalState) {
            PlayerState.Blackjack -> player.push()
            else -> player.win(true)
        }
        PlayerState.Busted -> player.lose()
        else -> when (dealer.finalState) {
            PlayerState.Blackjack -> player.lose()
            PlayerState.Busted -> player.win(false)
            else -> player.compareTotal(dealer.player.cards.total.value)
        }
    }

    private fun PlayerResult.compareTotal(dealerTotal: Int): Profit {
        val difference = player.cards.total.value - dealerTotal

        return when {
            difference > 0 -> win(false)
            difference == 0 -> push()
            else -> lose()
        }
    }

    private fun PlayerResult.win(blackjack: Boolean) = if (blackjack) player.bet.blackjackWin() else player.bet.win()
    private fun PlayerResult.push() = player.bet.push()
    private fun PlayerResult.lose() = player.bet.lose()
}
