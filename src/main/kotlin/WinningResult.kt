import GameResult.Companion.getApposite
import GameResult.DRAW
import GameResult.LOSE
import GameResult.WIN
import participant.Participant
import state.Blackjack
import state.Bust
import state.Stay

class WinningResult(private val participant: Participant) {
    private val participantScore: MutableMap<GameResult, Int> =
        mutableMapOf(
            WIN to 0,
            LOSE to 0,
            DRAW to 0,
        )

    fun versus(player: Participant): GambleResult {
        if (participant.name == player.name) {
            return GambleResult(
                win = participantScore[WIN] ?: 0,
                lose = participantScore[WIN] ?: 0,
                draw = participantScore[WIN] ?: 0,
            )
        }
        val result = compare(player)
        participantScore[result] = participantScore.getValue(result) + 1
        val playerResult = getApposite(result)
        return GambleResult.from(playerResult)
    }

    private fun compare(player: Participant): GameResult {
        return when (participant.state) {
            is Bust -> if (player.state is Bust) WIN else LOSE
            is Blackjack -> if (player.state is Blackjack) DRAW else WIN
            is Stay ->
                when {
                    player.state is Bust -> WIN
                    participant.score() < player.score() -> LOSE
                    participant.score() == player.score() -> DRAW
                    else -> WIN
                }

            else -> throw IllegalStateException()
        }
    }
}
