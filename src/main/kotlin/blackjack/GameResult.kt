package blackjack

import blackjack.participant.AbstractPlayer

class GameResult(
    players: List<AbstractPlayer>
) {
    val resultMap: Map<String, String>

    init {
        val dealer = players.first { it.isDealer() }
        val gamePlayers = players.filter { !it.isDealer() }

        val (winCount, loseCount) = when {
            dealer.isBust -> gamePlayers.partition { !it.isBust }
            else -> gamePlayers.partition { it.resultScore() < dealer.resultScore() }
        }

        val playerResult = mutableMapOf<String, String>().apply {
            winCount.map { this[it.name] = WIN }
            loseCount.map { this[it.name] = LOSE }
        }

        resultMap = mapOf(
            dealer.name to "${winCount.size} 승 ${loseCount.size} 패"
        ) + playerResult
    }

    companion object {
        private const val WIN: String = "승"
        private const val LOSE: String = "패"
    }
}
