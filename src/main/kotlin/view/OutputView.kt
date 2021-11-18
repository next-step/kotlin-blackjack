package view

import dto.PlayerDto
import dto.PlayersDto

object OutputView {
    private const val COMMA_SEPARATOR = ", "

    fun printStarted(players: PlayersDto) {
        val names = players.joinToString(COMMA_SEPARATOR) { it.name }
        println("${names}에게 각각 2장의 카드를 나누었습니다.")
        printPlayers(players)
    }

    fun printResult(players: PlayersDto) {
        println("\n## 최종 결과")
        printPlayers(players)
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
