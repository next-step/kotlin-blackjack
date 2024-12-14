package blackjack.result

import blackjack.domain.GameResult
import blackjack.participant.PlayerName

data class ParticipantResult(
    val name: PlayerName,
    val results: List<GameResult>,
) {
    fun getWinCount(): Int {
        return results.count { it == GameResult.WIN }
    }

    fun getLoseCount(): Int {
        return results.count { it == GameResult.LOSE }
    }

    fun getDrawCount(): Int {
        return results.count { it == GameResult.DRAW }
    }
}