package blackjack.result

import blackjack.participant.GameResult
import blackjack.participant.GameResult.DRAW
import blackjack.participant.GameResult.LOSE
import blackjack.participant.GameResult.WIN
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
