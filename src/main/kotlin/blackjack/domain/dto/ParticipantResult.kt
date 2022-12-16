package blackjack.domain.dto

import blackjack.domain.WinOrLose

data class ParticipantResult(
    val name: String,
    val winOrLose: WinOrLose,
) {
    fun isWin(): Boolean {
        return winOrLose == WinOrLose.WIN
    }
}
