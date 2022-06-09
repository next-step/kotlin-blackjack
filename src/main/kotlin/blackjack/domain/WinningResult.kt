package blackjack.domain

class WinningResult(val player: Player) {
    var winCount = 0
        private set
    var loseCount = 0
        private set
    var drawCount = 0
        private set

    fun updateResult(result: ResultStatus) {
        when (result) {
            ResultStatus.Win -> winCount++
            ResultStatus.Lose -> loseCount++
            ResultStatus.Draw -> drawCount++
        }
    }
}
