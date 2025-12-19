package service

import domain.Dealer
import domain.GameOutcome
import domain.Player

class BlackjackResultCalculator {

    fun profitReport(players: List<Player>, dealer: Dealer) {
        players.forEach { player ->
            val gameOutcome = gameOutcomeOf(player, dealer)
            player.profit = gameOutcome.profitOf(player.bettingAmount)
        }
        dealer.profit = -players.sumOf { it.profit }
    }

    private fun gameOutcomeOf(player: Player, dealer: Dealer): GameOutcome = when {
        player.isBust() -> GameOutcome.PLAYER_BUST
        dealer.isBust() -> GameOutcome.DEALER_BUST

        player.isBlackjack() && dealer.isBlackjack() -> GameOutcome.PUSH
        player.isBlackjack() -> GameOutcome.PLAYER_BLACKJACK
        dealer.isBlackjack() -> GameOutcome.DEALER_BLACKJACK

        player.score() > dealer.score() -> GameOutcome.PLAYER_WIN
        player.score() < dealer.score() -> GameOutcome.DEALER_WIN
        else -> GameOutcome.PUSH
    }
}
