package blackjack.domain

import blackjack.domain.member.Dealer
import blackjack.domain.member.LosePlayer
import blackjack.domain.member.Player
import blackjack.domain.member.Players
import blackjack.domain.member.WinPlayer

class GameResult private constructor(
    private val resultPlayers: List<Player>
) {
    val winPlayers: List<Player>
        get() = resultPlayers.filterIsInstance<WinPlayer>()
    val losePlayers: List<Player>
        get() = resultPlayers.filterIsInstance<LosePlayer>()

    val dealerLoseCount: Int
        get() = winPlayers.size
    val dealerWinCount: Int
        get() = losePlayers.size

    companion object {
        fun init(dealer: Dealer, players: Players): GameResult {
            return GameResult(players.toResultPlayers(dealer))
        }
    }
}
