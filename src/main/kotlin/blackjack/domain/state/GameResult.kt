package blackjack.domain.state

import blackjack.domain.player.Player

class GameResult(private val dealer: Player, private val users: List<Player>) {
    val winCountDealer =
        users.filter {
            dealer.comparePoints(it) == ResultState.WIN
        }.size

    val loseCountDealer =
        users.filter {
            dealer.comparePoints(it) == ResultState.LOSE
        }.size
    val drawCount = users.size - winCountDealer - loseCountDealer

    fun getGameUserResult(): List<String> {
        val results =
            users.map {
                "${it.name}: ${dealer.comparePoints(it).not().displayMessage}"
            }
        return results
    }
}
