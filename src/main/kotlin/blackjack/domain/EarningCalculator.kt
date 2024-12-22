package blackjack.domain

import blackjack.domain.Participant.Dealer
import blackjack.domain.Participant.Player

class EarningCalculator(private val dealer: Dealer) {
    fun calculatePlayerEarnings(player: Player): Double =
        when {
            isDealerBustedButPlayerNot(player) -> calculateWinningAmount(player)
            player.hasBusted() -> calculateLosingAmount(player)
            isBlackjackDraw(player) -> calculateDrawAmount(player)
            player.isBlackjack() -> calculateBlackjackWinningAmount(player)
            else -> calculateWinningAmount(player)
        }

    fun dealerMoney(players: List<Player>): Double {
        return players.sumOf { calculatePlayerEarnings(it) } * -1
    }

    private fun isDealerBustedButPlayerNot(player: Player): Boolean {
        return dealer.hasBusted() && !player.hasBusted()
    }

    private fun isBlackjackDraw(player: Player): Boolean {
        return dealer.isBlackjack() && player.isBlackjack()
    }

    private fun calculateWinningAmount(player: Player): Double {
        return player.bettingAmount * 1.0
    }

    private fun calculateLosingAmount(player: Player): Double {
        return player.bettingAmount * -1.0
    }

    private fun calculateDrawAmount(player: Player): Double {
        return player.bettingAmount.toDouble()
    }

    private fun calculateBlackjackWinningAmount(player: Player): Double {
        return player.bettingAmount * 1.5
    }
}
