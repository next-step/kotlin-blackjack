package service

import domain.Dealer
import domain.GameOutcome
import domain.GameOutcome.*
import domain.Player
import domain.SignedMoney

class BlackjackResultCalculator {

    fun profitReport(players: List<Player>, dealer: Dealer) {
        players.forEach { player ->
            val gameOutcome = gameOutcomeOf(player, dealer)
            player.applyProfit(gameOutcome.profitOf(player.bet))
        }

        val totalPlayerProfit: SignedMoney =
            players.fold(SignedMoney(0)) { acc, player -> acc + player.profit }

        dealer.applyProfit(-totalPlayerProfit)
    }

    private fun gameOutcomeOf(player: Player, dealer: Dealer): GameOutcome = when {
        player.isBust() -> PLAYER_BUST
        dealer.isBust() -> DEALER_BUST

        player.isBlackjack() && dealer.isBlackjack() -> PUSH
        player.isBlackjack() -> PLAYER_BLACKJACK
        dealer.isBlackjack() -> DEALER_BLACKJACK

        player.score() > dealer.score() -> PLAYER_WIN
        player.score() < dealer.score() -> DEALER_WIN
        else -> PUSH
    }
}
