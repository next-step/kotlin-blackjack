package blackjack.domain

import blackjack.domain.MatchResult.DRAW
import blackjack.domain.MatchResult.LOSS
import blackjack.domain.MatchResult.WIN
import blackjack.domain.dto.DealerGameResult
import blackjack.domain.dto.PlayerGameResult

data class GameResult(
    val dealerGameResult: DealerGameResult,
    val playerGameResults: List<PlayerGameResult>,
) {
    companion object {
        fun from(participants: List<Participant>): GameResult {
            val (players, dealer) = Participant.separate(participants)
            val playerGameResults = players.map { player -> PlayerGameResult(player, player.compareScore(dealer)) }
            return GameResult(
                DealerGameResult(
                    winCount = playerGameResults.count { it.result == LOSS },
                    lossCount = playerGameResults.count { it.result == WIN },
                    drawCount = playerGameResults.count { it.result == DRAW },
                ),
                playerGameResults,
            )
        }
    }
}
