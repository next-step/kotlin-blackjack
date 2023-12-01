package blackjack_dealer.ui

import blackjack_dealer.dto.ParticipantBetAmount

object InputView {
    fun inputParticipantsName(): String {
        val participants = readln().replace(", ", ",")
        participants.validateInputValue()
        return participants
    }

    fun inputBetAmount(): ParticipantBetAmount {
        val inputAmount = readln().toInt()
        return ParticipantBetAmount(inputAmount)
    }

    fun inputGetOneMoreCard(): Boolean {
        val input = readln()
        return input == "y"
    }
}
