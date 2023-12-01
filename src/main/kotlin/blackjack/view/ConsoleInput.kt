package blackjack.view

import blackjack.domain.player.Participant
import blackjack.domain.card.State

object ConsoleInput {

    private const val NAME_DELIMITER = ","

    fun inputNamesOfPlayer(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return inputNotEmptyString("공백을 입력할 수 없습니다. 다시 입력해주세요!")
            .split(NAME_DELIMITER)
    }

    private fun inputNotEmptyString(errorMessage: String): String {
        var input: String

        do {
            input = readln()
            if (input.isBlank()) println(errorMessage)
        } while (input.isBlank())

        return input
    }

    fun inputHitAndStay(participant: Participant): State {
        println()
        println("${participant.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        val input = inputNotEmptyString("공백을 입력할 수 없습니다. 다시 입력해주세요!")

        return when (input.lowercase()) {
            "y" -> State.HIT
            "n" -> State.STAY
            else -> {
                println("y 또는 n을 입력해주세요.")
                inputHitAndStay(participant)
            }
        }
    }

    fun inputBet(name: String): Int {
        println("${name}의 배팅 금액은?")

        return inputNotEmptyString("공백을 입력할 수 없습니다. 다시 입력해주세요!").toInt()
    }
}
