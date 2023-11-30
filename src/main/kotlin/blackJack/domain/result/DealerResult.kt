package blackJack.domain.result

class DealerResult(val win: Int, val draw: Int, val lose: Int) {
    companion object {
        fun calculateResult(playersResults: PlayersResult): DealerResult {
            val win = playersResults.countPlayerWin()
            val draw = playersResults.countPlayerDraw()
            val lose = playersResults.countPlayerLose()
            return DealerResult(win, draw, lose)
        }
    }
}
