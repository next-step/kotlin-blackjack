package blackjack.view

import blackjack.domain.player.Player

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

    fun inputGettingOneMoreCard(player: Player): Boolean {
        println()
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        val input = inputNotEmptyString("공백을 입력할 수 없습니다. 다시 입력해주세요!")

        return when (input.lowercase()) {
            "y" -> true
            "n" -> false
            else -> {
                println("y 또는 n을 입력해주세요.")
                inputGettingOneMoreCard(player)
            }
        }
    }
}
