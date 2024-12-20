package blackjack.domain

import blackjack.domain.Participant.Dealer
import blackjack.domain.Participant.Player

class EarningCalculator(private val dealer: Dealer) {
    fun playerMoney(player: Player): Double {
        if (dealer.hasBusted() && player.hasBusted().not()) {
            return (player.bettingAmount * 2).toDouble()
        }

        if (dealer.isBlackjack() && player.isBlackjack()) {
            return player.bettingAmount.toDouble()
        }

        if (player.isBlackjack()) {
            return player.bettingAmount * 1.5
        }

        return (player.bettingAmount * -1).toDouble()
    }

    fun dealerMoney(players: List<Player>): Double {
        return players.sumOf { playerMoney(it) } * -1
    }
}
