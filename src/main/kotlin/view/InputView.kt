package view

import model.Player
import kotlin.system.exitProcess

class InputView {
    fun inputPlayer(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val list = mutableListOf<Player>()
        val inputString = readLine()
        checkNotNull(inputString)
        try {
            require(NAME_REGEX.matches(inputString))
        } catch (ex: IllegalArgumentException) {
            println("잘못된 값을 입력하였습니다")
            exitProcess(0)
        }

        return list.toList()
    }

    companion object {
        val NAME_REGEX = Regex(pattern = "^[A-Za-z0-9,\\s]+\$")
    }
}