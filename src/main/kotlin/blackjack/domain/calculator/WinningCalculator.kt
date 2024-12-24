package blackjack.domain.calculator

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.status.PlayerStatus
import blackjack.domain.status.PlayerStatus.*

object WinningCalculator {
    fun calculatorGameResult(
        players: Players,
        dealer: Dealer,
    ) {
        if (dealer.isBust()) {
            handleDealerBust(players)
            return
        }

        players.forEach { player ->
            val status = calculateFinalStatus(player, dealer)
            player.updateStatus(status)
        }
    }

    private fun handleDealerBust(players: Players) {
        players.updateAllStatus(WIN)
    }

    private fun calculateFinalStatus(player: Player, dealer: Dealer): PlayerStatus {
        return when {
            player.playerStatus == BLACKJACK -> BLACKJACK
            dealer.isBust() || player.calculateCard() > dealer.calculateCard() -> WIN
            player.isBust() || player.calculateCard() < dealer.calculateCard() -> LOSE
            else -> DRAW
        }
    }
}
