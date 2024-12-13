package blackjack.step2.view

import blackjack.step2.domain.Player

object ConsoleInputHandler {
    fun inputPlayerNamesAndBets(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(",").map { it.trim() }.filter { it.isNotBlank() }
        return names.map { name ->
            println("${name}의 배팅 금액은?")
            val bet = readln().toIntOrNull() ?: inputBetAgain(name)
            Player(name = name, bet = bet)
        }
    }

    private fun inputBetAgain(name: String): Int {
        println("잘못된 금액입니다. 다시 입력하세요.")
        return readln().toIntOrNull() ?: inputBetAgain(name)
    }
}
