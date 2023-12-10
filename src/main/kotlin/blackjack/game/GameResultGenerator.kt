package blackjack.game

import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player

object GameResultGenerator {

    fun generateGameResult(players: List<Player>, dealer: Dealer): GameResult {
        val resultMap = mapOf(Name("딜러") to dealer.bettingAmount) + players.map { player ->
            val result = dealer.matchingScore(player)
            val bettingAmount = player.status.calculateBettingAmount(result, player.bettingAmount)
            player.name to bettingAmount
        }

        return GameResult(resultMap)
    }
}