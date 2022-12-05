package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

const val DELIMITER_NAMES = ","

class ConsoleInput {

    fun inputPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val names = readln()
        require(names.isNotBlank()) { "잘못된 입력값입니다." }

        val players = Players(splitNames(names).map { Player(it) })
        println()
        return players
    }

    private fun splitNames(names: String): List<String> = names.split(DELIMITER_NAMES).map { it.trim() }
}
