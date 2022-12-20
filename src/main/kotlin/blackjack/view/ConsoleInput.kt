package blackjack.view

import blackjack.domain.BettingAmount
import blackjack.domain.GamePlayer
import blackjack.domain.Name
import blackjack.domain.PlayerInfo
import blackjack.domain.Players

object ConsoleInput {
    private const val DELIMITER_NAMES = ","

    fun inputPlayersInfo(): Players {
        val names = inputPlayerNames()
        val gamePlayers = names.map { GamePlayer(PlayerInfo(it, inputBettingAmount(it.value))) }
        return Players(gamePlayers)
    }

    private fun inputPlayerNames(): List<Name> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return runCatching {
            val value = readln()
            require(value.isNotBlank()) { "잘못된 입력값입니다." }

            val names = getPlayerNames(value)
            println()
            names
        }.fold(
            onSuccess = { it },
            onFailure = { e ->
                println(e.message)
                inputPlayerNames()
            }
        )
    }

    private fun inputBettingAmount(name: String): BettingAmount {
        println("${name}의 배팅 금액은?")
        val bettingAmount = BettingAmount(readln().toInt())
        println()
        return bettingAmount
    }

    tailrec fun inputScratch(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        val isHit = readln().lowercase()
        return if (isHit in listOf("y", "n")) {
            "y" == isHit
        } else {
            println("y 또는 n으로 의사를 입력해주세요.")
            inputScratch(name)
        }
    }

    private fun getPlayerNames(names: String): List<Name> = splitNames(names).map { Name(it) }
    private fun splitNames(names: String): List<String> = names.split(DELIMITER_NAMES).map { it.trim() }
}
