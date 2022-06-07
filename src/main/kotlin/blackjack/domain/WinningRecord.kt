package blackjack.domain

class WinningRecord {
    var winCount = 0
        private set
    var loseCount = 0
        private set

    fun updateRecord(isWin: Boolean) {
        if (isWin) winCount++
        else loseCount++
    }
}
