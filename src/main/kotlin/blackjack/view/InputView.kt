package blackjack.view

import blackjack.domain.player.Player

object InputView {
    fun getPlayersName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun getNeedOneMoreCard(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readlnOrNull()
        return when(input?.trim()?.lowercase()) {
            "y" -> true
            "n" -> false
            else -> {
                println("y또는 n만 입력할 수 있습니다.")
                getNeedOneMoreCard(player)
            }
        }
    }
}
