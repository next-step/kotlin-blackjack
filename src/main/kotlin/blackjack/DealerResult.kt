package blackjack

import blackjack.domain.PlayerGameResult
import blackjack.domain.PlayerWinLoseResult

class DealerResult(playerGameResults: List<PlayerGameResult>) {
    var winCount: Int = 0
        private set
    var loseCount: Int = 0
        private set
    var pushCount: Int = 0
        private set

    init {
        playerGameResults.forEach {
            when (it.result) {
                PlayerWinLoseResult.WIN -> loseCount++
                PlayerWinLoseResult.LOSE -> winCount++
                PlayerWinLoseResult.PUSH -> pushCount++
            }
        }
    }
}
