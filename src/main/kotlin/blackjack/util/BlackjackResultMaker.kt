package blackjack.util

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.dto.PlayerGameResult

object BlackjackResultMaker {
    fun result(dealer: Dealer, players: List<Player>): List<PlayerGameResult> {
        val playerGameResults = players.map { PlayerGameResult(it, it.flip(dealer)) }
        val dealerBettingAmount = -playerGameResults.sumOf { it.winningAmount }
        return listOf(PlayerGameResult(dealer, dealerBettingAmount)) + playerGameResults
    }
}
