package blackjack.presentation

import blackjack.core.Player
import blackjack.core.Players

object ResultView {
    fun printStandby(players: Players) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(getPlayerNames(players))
        stringBuilder.append(STRING_STANDBY)
        stringBuilder.append(STRING_NEWLINE)
        println(stringBuilder.toString())

        players.forEach {
            println(getPlayerCards(it))
        }
    }

    private fun getPlayerNames(players: Players): String {
        return players.map { it.name }
            .joinToString(",", "", "")
    }

    fun printPlayerCards(player: Player) {
        println(getPlayerCards(player))
    }

    fun printResult(players: Players) {
        players.forEach {
            val stringBuilder = StringBuilder()
            stringBuilder.append(getPlayerCards(it))
            stringBuilder.append(STRING_RESULT)
            stringBuilder.append(it.point())
            println(stringBuilder.toString())
        }
    }

    private fun getPlayerCards(player: Player): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(player.name)
        stringBuilder.append(STRING_CARD)
        stringBuilder.append(player.getCardNames())
        return stringBuilder.toString()
    }

    private const val STRING_CARD = "카드:"
    private const val STRING_RESULT = "- 결과:"

    private const val STRING_STANDBY = "에게 2장의 나누었습니다."
    private const val STRING_NEWLINE = "\n"
}
