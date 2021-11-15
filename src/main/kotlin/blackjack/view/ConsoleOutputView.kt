package blackjack.view

import blackjack.view.dto.PlayerDto
import blackjack.view.dto.PlayersDto

object ConsoleOutputView {

    fun giveFirstTwoCards(players: PlayersDto) {
        val playerNames = players.joinToString(",") { it.name }
        println("${playerNames}에게 2장의 카드를 나누었습니다.")
        println()
        players.forEach { printPlayer(it) }
    }

    fun printResult(players: PlayersDto) {
        players.forEach {
            printPlayer(it, lastNewLine = false)
            println(" - 결과: ${it.score}")
        }
    }

    fun printPlayer(player: PlayerDto, lastNewLine: Boolean = true) {
        val cardNames = player.cards.joinToString(",") { it.name }
        print("${player.name}카드: $cardNames")
        if (lastNewLine) {
            println()
        }
    }
}
