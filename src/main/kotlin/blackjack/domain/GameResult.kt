package blackjack.domain

import blackjack.domain.member.LosePlayers
import blackjack.domain.member.Players
import blackjack.domain.member.WinnerPlayers

class GameResult(
    val winnerPlayers: WinnerPlayers,
    val losePlayers: LosePlayers
) {
    val dealerLoseCount: Int
        get() = winnerPlayers.size
    val dealerWinCount: Int
        get() = losePlayers.size

    companion object {
        fun winAllPlayers(players: Players): GameResult {
            return GameResult(players.toWinnerPlayers(), LosePlayers(emptyList()))
        }

        private fun Players.toWinnerPlayers() = WinnerPlayers(this.items)
    }
}
