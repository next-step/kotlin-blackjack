package blackjack.view

import blackjack.domain.Deck.Companion.INITIAL_CARD_COUNT
import blackjack.domain.Player
import blackjack.domain.Players

object ConsoleOutput {
    fun printInitialCards(dealer: Player, players: Players) {
        println("달러와 ${players.list.joinToString { it.name.value }}에게 ${INITIAL_CARD_COUNT}장의 카드를 나누었습니다.")
        printPlayerCards(dealer)
        players.list.map { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(player: Player) = println(getPlayerInfo(player))

    fun printResultCards(dealer: Player, players: Players) {
        printResultCards(dealer)
        players.list.map { printResultCards(it) }
        println()
    }

    private fun printResultCards(player: Player) {
        println("${getPlayerInfo(player)} - 결과: ${player.countingCard()}")
    }

    private fun getPlayerInfo(player: Player) = "${player.name.value} 카드: ${player.cards}"
}
