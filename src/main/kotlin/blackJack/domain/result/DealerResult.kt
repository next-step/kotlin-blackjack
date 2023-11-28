package blackJack.domain.result

import blackJack.domain.enums.BlackjackResult

class DealerResult(val win: Int, val draw: Int, val lose: Int) {
    companion object {
        fun calculateResult(playersResults: PlayersResult): DealerResult {
            val win = playersResults.playersResult.count { it.result == BlackjackResult.LOSE }
            val draw = playersResults.playersResult.count { it.result == BlackjackResult.DRAW }
            val lose = playersResults.playersResult.count { it.result == BlackjackResult.WIN }
            return DealerResult(win, draw, lose)
        }
    }
}
