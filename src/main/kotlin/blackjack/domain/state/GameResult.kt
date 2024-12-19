package blackjack.domain.state

import blackjack.domain.player.Player

class GameResult(private val dealer: Player, private val users: List<Player>) {
    private val dealerResult: String

    init {
        val winCount =
            users.filter {
                dealer.comparePoints(it) == ResultState.WIN
            }.size
        val loseCount =
            users.filter {
                dealer.comparePoints(it) == ResultState.LOSE
            }.size
        val drawCount = users.size - winCount + loseCount
        dealerResult = "${winCount}승 ${drawCount}무 ${loseCount}패"
    }

    fun getDealerResult(): String {
        return dealerResult
    }

    fun getGameUserResult(): List<String> {
        val results =
            users.map {
                "${it.name}: ${dealer.comparePoints(it).not().displayMessage}"
            }
        return results
    }
}
