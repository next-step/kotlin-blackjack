package blackjack_dealer.ui

object InputView {
    fun inputParticipantsName(): String {
        val participants = readln().replace(", ", ",")
        participants.validateInputValue()
        return participants
    }

    fun inputBetAmount(): Int {
        val inputAmount = readln().toInt()
        inputAmount.validateBetAmountValue()
        return inputAmount
    }

    fun inputGetOneMoreCard(): Boolean {
        val input = readln()
        return input == "y"
    }
}
