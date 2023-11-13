package blackjack.ui

object InputView {
    fun inputParticipantName(): String {
        val participants = readln()
        return participants.replace(",", ", ")
    }
}
