package view

import model.Player

object InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.")
        return readln().ifNullEmpty().split(",").map { it.trim() }
    }

    fun inputBetAmount(player: Player): Long {
        println("${player.name}의 배팅 금액은?")
        val bet = readlnOrNull()?.toLongOrNull() ?: throw IllegalArgumentException("금액을 입력해주세요.")
        require(bet > 0) { "베팅 금액은 0원보다 커야 합니다." }
        println()
        return bet
    }

    fun inputOneMoreCard(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln().ifNullEmpty().trim().lowercase()

        require(input == "y" || input == "n") { "y 또는 n으로 입력해주세요." }
        return input == "y"
    }

    private fun String?.ifNullEmpty(): String {
        return this ?: ""
    }

    fun <T> tryUntilSuccess(block: () -> T): T {
        while (true) {
            try {
                return block()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
