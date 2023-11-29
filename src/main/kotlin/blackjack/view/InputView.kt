package blackjack.view

import blackjack.Participant
import blackjack.view.InputType.CARD_ADD_QUESTION
import blackjack.view.InputType.PARTICIPANT_NAMES

class InputView {
    fun inputNumber(inputType: InputType): Int {
        println(inputType.question)
        return readln().toInt()
    }

    fun inputNames(): List<String> {
        println(PARTICIPANT_NAMES.question)
        return readln().split(",").map { it.trim() }
    }

    fun inputAnswer(participant: Participant) {
        println(CARD_ADD_QUESTION.question.format(participant.name))
        participant.isReceiveCard(readln())
    }
}
