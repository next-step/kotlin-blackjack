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

        val (winCount, loseCount) = when {
            dealer.isBust -> players.partition { !it.isBust }
            else -> players.partition { it.resultScore() < dealer.resultScore() }
        }

        val playerResult = mutableMapOf<Name, Result>().apply {
            winCount.map { this[it.name] = Result.Win() }
            loseCount.map { this[it.name] = Result.Lose() }
        }

        resultMap = mapOf(
            Name("딜러") to Result.DealerResult(winCount.size, loseCount.size)
        ) + playerResult
    }

    companion object {
        private const val WIN: String = "승"
        private const val LOSE: String = "패"
    }
}
