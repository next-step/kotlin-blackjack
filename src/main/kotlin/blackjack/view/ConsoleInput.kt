package blackjack.view

import blackjack.domain.GamePlayer
import blackjack.domain.Player
import blackjack.domain.Players

object ConsoleInput {
    private const val DELIMITER_NAMES = ","

    fun inputPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return runCatching {
            val names = readln()
            require(names.isNotBlank()) { "잘못된 입력값입니다." }

            val players = getPlayers(names)
            println()
            players
        }.fold(
            onSuccess = { it },
            onFailure = { e ->
                println(e.message)
                inputPlayers()
            }
        )
    }

    tailrec fun inputScratch(player: Player): Boolean {
        println("${player.name.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        val isHit = readln().lowercase()
        return if (isHit in listOf("y", "n")) {
            "y" == isHit
        } else {
            println("y 또는 n으로 의사를 입력해주세요.")
            inputScratch(player)
        }
    }

    private fun getPlayers(names: String): Players = Players(splitNames(names).map { GamePlayer(it) })
    private fun splitNames(names: String): List<String> = names.split(DELIMITER_NAMES).map { it.trim() }
}
