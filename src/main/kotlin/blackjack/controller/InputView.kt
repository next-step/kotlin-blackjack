package blackjack.controller

import blackjack.domain.Player

object InputView {

    private const val PLAYER_NAME_DELIMITER = ","

    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames = readln().split(PLAYER_NAME_DELIMITER).map { it.trim() }
        println("")
        return playerNames
    }

    fun askPlayerHit(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val yOrN = readln()
        return yOrN == "y"
    }
}
