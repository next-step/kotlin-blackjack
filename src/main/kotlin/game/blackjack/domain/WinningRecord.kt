package game.blackjack.domain

data class WinningRecord(private var win: Int = 0, private var lose: Int = 0) {

    fun recordWin() {
        win++
    }

    fun recordLose() {
        lose++
    }

    fun win() = win

    fun lose() = lose
}
