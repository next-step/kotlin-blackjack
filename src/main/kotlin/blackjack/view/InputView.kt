package blackjack.view

import blackjack.domain.Participant
import blackjack.view.InputType.CARD_ADD_QUESTION
import blackjack.view.InputType.PARTICIPANT_NAMES

class InputView {
    fun inputNames(): List<String> {
        println(PARTICIPANT_NAMES.question)
        return readln().split(",").map { it.trim() }
    }

    fun inputAnswer(participant: Participant): Boolean {
        println(CARD_ADD_QUESTION.question.format(participant.name))
        return isReceiveCardAnswer(readln())
    }

    private fun isReceiveCardAnswer(answer: String): Boolean {
        return when (answer) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("y 또는 n을 입력해주세요.")
        }
    }
}
