package blackjack

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
        val playerResult = mutableMapOf<Name, BettingAmount>()

        players.forEach {
            val result = dealer.matchingScore(it)
            playerResult[it.name] = it.status.calculateBettingAmount(result, it.bettingAmount)
        }

        resultMap = mapOf(Name("딜러") to dealer.bettingAmount) + playerResult
    }
}
