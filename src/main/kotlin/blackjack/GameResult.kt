package blackjack

import blackjack.participant.Dealer
import blackjack.participant.Player

class GameResult(
    players: List<Player>,
    dealer: Dealer
) {
    val resultMap: Map<String, String>

    init {

        val (winCount, loseCount) = when {
            dealer.isBust -> players.partition { !it.isBust }
            else -> players.partition { it.resultScore() < dealer.resultScore() }
        }

        val playerResult = mutableMapOf<String, String>().apply {
            winCount.map { this[it.name] = WIN }
            loseCount.map { this[it.name] = LOSE }
        }

        resultMap = mapOf(
            "딜러" to "${winCount.size} 승 ${loseCount.size} 패"
        ) + playerResult
    }

    companion object {
        private const val WIN: String = "승"
        private const val LOSE: String = "패"
    }
}
