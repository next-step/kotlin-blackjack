package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Player
import blackjack.domain.Players

class OutputConsoleView {
    fun printInitCardMsg(players: Players) {
        println("${players.players.map { it.name }.joinToString(",")} 에게 2장의 카드를 나누었습니다.")
        players.players.map { printCards(it) }
        println()
    }

    fun printResult(players: Players) {
        println()
        players.players.map { println("${cardsToString(it)} - 결과: ${it.cardSum()}") }
    }

    fun printCards(player: Player) {
        println(cardsToString(player))
    }

    private fun cardsToString(player: Player): String {
        return "${player.name}카드: ${player.cards.joinToString(", ") { cardToString(it) }}"
    }

    private fun cardToString(card: Card): String {
        return "${card.number.display}${card.shape.display}"
    }
}
