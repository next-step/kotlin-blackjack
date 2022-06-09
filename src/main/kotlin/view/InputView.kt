package view

import domain.player.Player

object InputView {
    private const val PLAYER_NAME_DELIMITERS = ","

    fun inputPlayerName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(PLAYER_NAME_DELIMITERS)
    }

    fun askToDraw(player: Player): Boolean {
        println("${player.name}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> {
                println("y나 n을 입력하십시오")
                askToDraw(player)
            }
        }
    }
}