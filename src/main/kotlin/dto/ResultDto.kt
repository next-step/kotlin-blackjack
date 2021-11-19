package dto

import domain.player.Dealer
import domain.player.Players

class ResultDto(players: Players, dealer: Dealer) {
    val players = players.map { PlayerResultDto(it, dealer) }
    val dealer = PlayerDto(dealer)
    private val winCount = players.countWins(dealer)
    val dealerWin = "${players.size - winCount}승 ${winCount}패"
    val dealerProfit = players.sumOfProfits(dealer) * -1.0
}
