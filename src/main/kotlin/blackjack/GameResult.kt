package blackjack

import blackjack.participant.BettingAmount
import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import blackjack.participant.Result
import blackjack.participant.status.Blackjack

class GameResult(
    players: List<Player>,
    dealer: Dealer
) {
    val resultMap: Map<Name, Result>

    val resultMap2: Map<Name, BettingAmount>

    init {
        if (dealer.status is Blackjack) {


        }

        val winner = findWinner(players, dealer)
        val loser = findLoser(players, dealer)

        val playerResult = mutableMapOf<Name, Result>().apply {
            loser.map { this[it.name] = Result.Lose }
            winner.map { this[it.name] = Result.Win }
        }

        val playerResult2 = mutableMapOf<Name, BettingAmount>().apply {
            loser.map { this[it.name] = it.status.calculateBettingAmount(Result.Lose, it.bettingAmount) }
            winner.map { this[it.name] =it.status.calculateBettingAmount(Result.Win, it.bettingAmount) }
        }

        resultMap = mapOf(
            Name("딜러") to Result.DealerResult(loser.size, winner.size)
        ) + playerResult

        resultMap2 = mapOf(
            Name("딜러") to dealer.status.calculateBettingAmount(Result.DealerResult(loser.size, winner.size),dealer.bettingAmount)
        ) + playerResult2
    }

    private fun findWinner(players: List<Player>, dealer: Dealer): List<Player> {
        if (dealer.isBust) {
            return players.filter { !it.isBust }
        }

        return players.filter { !it.isBust }.filter { it.resultScore() > dealer.resultScore() }
    }

    private fun findLoser(players: List<Player>, dealer: Dealer): List<Player> {
        if (dealer.isBust) {
            return players.filter { it.isBust }
        }

        val bustPlayer = players.filter { it.isBust }
        val loser = players.filter { !it.isBust }.filter { it.resultScore() < dealer.resultScore() }

        return bustPlayer + loser
    }
}
