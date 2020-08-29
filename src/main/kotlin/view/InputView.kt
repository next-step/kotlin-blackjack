package view

import model.Player
import kotlin.system.exitProcess

object InputView {
    fun inputPlayers(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val list = mutableListOf<Player>()
        val inputString = readLine()
        checkNotNull(inputString)
        try {
            require(NAME_REGEX.matches(inputString))
            list.addAll(inputString.removeWhitespace().split(DELIMITER).map { Player(it) })
        } catch (ex: IllegalArgumentException) {
            println("잘못된 값을 입력하였습니다")
            exitProcess(128)
        }

        return list.toList()
    }

    fun receiveCard(player: Player): Boolean {
        println("${player.name.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val inputString = readLine()
        return inputString.equals("y")
    }

    private fun String.removeWhitespace(): String {
        return replace(" ", "")
    }

    private const val DELIMITER = ","
    private val NAME_REGEX = Regex(pattern = "^[A-Za-z0-9,\\s]+\$")
}
