package blackjack

import blackjack.domain.Player
import blackjack.domain.Players

object InputView {
    private const val DELIMITER_NAMES = ","

    fun inputPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val names = readln()
        require(names.isNotBlank()) { "잘못된 입력값입니다." }

        val splitNames = splitNames(names)
        val players = createPlayers(splitNames)
        println()
        return players
    }

    fun shouldHit(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val hit = readln()
        require(hit in listOf("y", "n")) { "y 또는 n으로 의사를 입력해주세요." }
        return "y" == hit
    }

    private fun createPlayers(names: List<String>): Players = Players(names.map { Player(it) })
    private fun splitNames(names: String): List<String> = names.split(DELIMITER_NAMES).map { it.trim() }
}
