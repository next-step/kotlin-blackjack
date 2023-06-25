package blackjack.domain.result

import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayers
import blackjack.domain.result.match.MatchStrategyProvider

object GameResultManager {
    fun getGameResults(players: GamePlayers, dealer: Dealer): List<GameResult> {
        val matchStrategy = MatchStrategyProvider[dealer]
        return players.players.map { GameResult(it, matchStrategy.match(dealer, it)) }
    }
}
