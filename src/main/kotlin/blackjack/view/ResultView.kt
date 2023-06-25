package blackjack.view

import blackjack.domain.BlackJack
import blackjack.domain.Player

private const val START_STRING = "에게 2장의 나누었습니다."

private const val CARD_STRING = "카드:"

object ResultView {
    private fun printCards(player: Player) {
        println("${player.name}$CARD_STRING ${player.cards.joinToString { it.char + it.shape }}")
    }

    fun printStart(game: BlackJack) {
        println("\n${game.players.joinToString { it.name }}$START_STRING")
        game.players.forEach { printCards(it) }
    }
}
