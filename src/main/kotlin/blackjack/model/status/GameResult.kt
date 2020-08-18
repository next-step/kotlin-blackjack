package blackjack.model.status

import blackjack.model.player.Player

class GameResult(
    private val players: List<Player>,
    private val dealer: Player
) {
    private val dealerScore: Map<Finish, Int> =
        getDealerScore().groupingBy { it }.eachCount()

    private fun getDealerScore() =
        players.map { it.getFinish(dealer.getScore()).reverse() }

    fun toDealerResultString() =
        dealerScore.map {
            "${it.value}${it.key.str}"
        }.joinToString(" ")

    fun playersResultString() =
        players.joinToString("\n") {
            "${it.name}:${it.getFinish(dealer.getScore())}"
        }
}
