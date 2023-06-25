package blackjack.view

import blackjack.Player

object InputView {
    fun getPlayers(): List<String> {
        println("게임에 참여할 사람이 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln()
        return input.split(",")
    }

    fun askCastCardToPlayer(player: Player): Boolean {
        println("${player.name} 은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("올바르지 않은 응답값입니다.")
        }
    }
}
