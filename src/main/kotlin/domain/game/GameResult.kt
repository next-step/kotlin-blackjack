package domain.game

import domain.dto.IssuedCardResult
import domain.player.Dealer
import domain.player.Players

class GameResult(players: Players, dealer: Dealer) {

    val revenueResult: RevenueResult
    val issuedCardsResult: IssuedCardResult

    init {
        val playersRevenues = players.associate { player -> player.name to player.getRevenue(dealer) }
        val dealerRevenue = playersRevenues.values.sumOf { -it }
        revenueResult = RevenueResult(dealerRevenue = dealerRevenue, playersRevenues = playersRevenues)
        issuedCardsResult = IssuedCardResult(players = players, dealer = dealer)
    }
}
