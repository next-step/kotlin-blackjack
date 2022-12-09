package blackjack.view

import blackjack.domain.INITIAL_CARD_COUNT
import blackjack.domain.Player
import blackjack.domain.Players

object ConsoleOutput {
    fun printInitialCards(players: Players) {
        println("${players.list.joinToString { it.name }}에게 ${INITIAL_CARD_COUNT}장의 카드를 나누었습니다.")
        players.list.map { println(getPlayerInfo(it)) }
        println()
    }

    fun printPlayerCards(player: Player) = println(getPlayerInfo(player))

    fun printGameResult(players: Players) {
        players.list.map { println("${getPlayerInfo(it)} - 결과: ${it.countingCard()}") }
    }

    fun printLine() = println()

    private fun getPlayerInfo(player: Player) = "${player.name}카드: ${player.cards}"
}
