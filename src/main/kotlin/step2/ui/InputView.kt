package step2.ui

import step2.domain.Player
import step2.domain.Players

object InputView {

    private const val READ_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PLAYER_DELIMITER = ","
    private const val READ_MORE_CARD_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)"

    fun readPlayer(): Players {
        println(READ_PLAYER_MESSAGE)
        val playerNames = readln().split(PLAYER_DELIMITER)
        println()

        return Players.of(playerNames)
    }

    fun askDrawMoreCard(player: Player): String {
        println(String.format(READ_MORE_CARD_MESSAGE, player.name))
        return readln()
    }
}
