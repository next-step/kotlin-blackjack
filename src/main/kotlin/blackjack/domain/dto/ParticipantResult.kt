package blackjack.domain.dto

import blackjack.domain.enums.WinOrLose

data class ParticipantResult(
    val name: String,
    val winOrLose: WinOrLose,
) {
    fun isWin(): Boolean {
        return winOrLose == WinOrLose.WIN
    }
}
