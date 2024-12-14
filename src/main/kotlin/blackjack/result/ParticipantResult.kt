package blackjack.result

import blackjack.domain.GameResult
import blackjack.domain.GameResult.DRAW
import blackjack.domain.GameResult.LOSE
import blackjack.domain.GameResult.WIN
import blackjack.participant.PlayerName

data class ParticipantResult(
    val name: PlayerName,
    val results: List<GameResult>,
) {
    fun getWinCount(): Int {
        return results.count { it == WIN }
    }

    fun getLoseCount(): Int {
        return results.count { it == LOSE }
    }

    fun getDrawCount(): Int {
        return results.count { it == DRAW }
    }
}
