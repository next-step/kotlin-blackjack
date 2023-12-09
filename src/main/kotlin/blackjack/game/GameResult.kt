package blackjack.game

import blackjack.participant.BettingAmount
import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player

class GameResult(
    players: List<Player>,
    dealer: Dealer
) {
    val resultMap: Map<Name, BettingAmount>

    init {
        resultMap = mapOf(Name("딜러") to dealer.bettingAmount) + players.map { player ->
            val result = dealer.matchingScore(player)
            val bettingAmount = player.status.calculateBettingAmount(result, player.bettingAmount)
            player.name to bettingAmount
        }
    }
}
