package blackjack.domain.game

import blackjack.domain.game.MatchResult.DRAW
import blackjack.domain.game.MatchResult.LOSE
import blackjack.domain.game.MatchResult.WIN
import blackjack.domain.game.dto.DealerGameResult
import blackjack.domain.game.dto.PlayerGameResult
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class GameResult(
    val dealerGameResult: DealerGameResult,
    val playerGameResults: List<PlayerGameResult>,
) {
    companion object {
        fun from(
            dealer: Dealer,
            players: List<Player>,
        ): GameResult {
            val playerGameResults = players.map { player -> PlayerGameResult(player, player.matchHand(dealer)) }
            return GameResult(
                DealerGameResult(
                    winCount = playerGameResults.count { it.result == LOSE },
                    loseCount = playerGameResults.count { it.result == WIN },
                    drawCount = playerGameResults.count { it.result == DRAW },
                ),
                playerGameResults,
            )
        }
    }
}
