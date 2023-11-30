package blackJack.domain.result

class DealerResult(val blackJackWin: Int, val win: Int, val draw: Int, val lose: Int) {
    companion object {
        fun calculateResult(playersResults: PlayersResult): DealerResult {
            val blackJackWin = playersResults.countPlayerBlackJackWin()
            val win = playersResults.countPlayerWin()
            val draw = playersResults.countPlayerDraw()
            val lose = playersResults.countPlayerLose()
            return DealerResult(blackJackWin, win, draw, lose)
        }
    }
}
