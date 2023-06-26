package blackjack.domain.result

import blackjack.domain.player.Players
import blackjack.domain.result.match.MatchStrategyProvider

object GameResultManager {
    fun getGameResults(players: Players): List<GameResult> {
        val dealer = players.getDealer()
        val matchStrategy = MatchStrategyProvider[dealer]
        return players.getGamePlayers().players.map { GameResult(it, matchStrategy.match(dealer, it)) }
    }
}
