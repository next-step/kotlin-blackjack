package blackjack.domain

data class ParticipantResult(
    val name: String,
    val winOrLose: WinOrLose,
) {
    fun isWin(): Boolean {
        return winOrLose == WinOrLose.WIN
    }
}
