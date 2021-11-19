package view

import dto.PlayerDto
import dto.PlayersDto

object OutputView {
    private const val COMMA_SEPARATOR = ", "

    fun printStarted(players: PlayersDto) {
        val names = players.joinToString(COMMA_SEPARATOR) { it.name }
        println("\n${names}에게 각각 2장의 카드를 나누었습니다.")
        printPlayers(players)
    }

    fun printResult(players: PlayersDto) {
        printPlayers(players)
        println("\n## 최종 수익\n")
    }

    fun printDealerMessage(drawable: Boolean) {
        val message = if (drawable)
            "딜러는 16이하라 한장의 카드를 더 받았습니다."
        else "딜러가 16초과라 카드를 더 받지 않습니다."
        println("\n$message\n")
    }

    fun printPlayer(player: PlayerDto) {
        val playerName = "${player.name}카드:"
        val playerCards = player.cards.joinToString(COMMA_SEPARATOR)
        val playerScore = "- 결과: ${player.score()}"
        println("$playerName $playerCards $playerScore")
    }

    private fun printPlayers(players: PlayersDto) {
        players.forEach { printPlayer(it) }
    }
}
