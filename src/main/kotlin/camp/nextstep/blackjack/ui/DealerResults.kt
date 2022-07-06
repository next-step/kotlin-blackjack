package camp.nextstep.blackjack.ui

import camp.nextstep.blackjack.game.GameResult

class DealerResults(gameResults: List<GameResult>) {
    val winCount = gameResults.count { it == GameResult.WIN }
    val loseCount = gameResults.count { it == GameResult.LOSE }
    val drawCount = gameResults.count { it == GameResult.DRAW }

    override fun toString(): String {
        return "$winCount 승  $loseCount 패  $drawCount 무"
    }
}
