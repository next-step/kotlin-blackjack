package domain

import domain.gamer.player.Players

data class Result(
    val winners: Players,
    val losers: Players,
) {
    val numOfWinner: Int = winners.numberOfPlayers()
    val numOfLoser: Int = losers.numberOfPlayers()
}
