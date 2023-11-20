package blackjack_dealer.ui

object InputView {
    fun inputParticipantsName(): String {
        val participants = readln().replace(", ", ",")
        participants.validateInputValue()
        return participants
    }
}
