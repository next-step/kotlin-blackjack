package domain

import domain.player.Players

data class Result(
    val winners: Players,
    val losers: Players,
) {
    val numOfWinner = winners.list.size
    val numOfLoser = losers.list.size
}
