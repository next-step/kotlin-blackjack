package blackjack

import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import blackjack.participant.Result

class GameResult(
    players: List<Player>,
    dealer: Dealer
) {
    val resultMap: Map<Name, Result>

    init {
        val winner = findWinner(players, dealer)
        val loser = findLoser(players, dealer)

        val playerResult = mutableMapOf<Name, Result>().apply {
            loser.map { this[it.name] = Result.Lose() }
            winner.map { this[it.name] = Result.Win() }
        }

        resultMap = mapOf(
            Name("딜러") to Result.DealerResult(loser.size, winner.size)
        ) + playerResult
    }

    private fun findWinner(players: List<Player>, dealer: Dealer): List<Player> {
        if (dealer.isBust) {
            return players.filter { !it.isBust }
        }

        return players.filter { !it.isBust }.filter {  it.resultScore() > dealer.resultScore() }
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
