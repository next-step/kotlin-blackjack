package blackjack.presentation

import blackjack.core.Player
import blackjack.core.Players

object ResultView {
    fun printStandby(players: Players) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(players.getNames())
        stringBuilder.append(STR_STANDBY)
        stringBuilder.append(STR_NEWLINE)
        println(stringBuilder.toString())

        players.forEach {
            println(getPlayerCards(it))
        }
    }

    fun printPlayerCards(player: Player) {
        println(getPlayerCards(player))
    }

    fun printResult(players: Players) {
        players.forEach {
            val stringBuilder = StringBuilder()
            stringBuilder.append(getPlayerCards(it))
            stringBuilder.append(STR_RESULT)
            stringBuilder.append(it.point())
            println(stringBuilder.toString())
        }
    }

    private fun getPlayerCards(player: Player): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(player.name)
        stringBuilder.append(STR_CARD)
        stringBuilder.append(player.getCardNames())
        return stringBuilder.toString()
    }

    private const val STR_CARD = "카드:"
    private const val STR_RESULT = "- 결과:"

    private const val STR_STANDBY = "에게 2장의 나누었습니다."
    private const val STR_NEWLINE = "\n"
}
