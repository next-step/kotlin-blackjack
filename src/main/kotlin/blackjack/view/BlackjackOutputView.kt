package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Player
import blackjack.domain.Suit

object BlackjackOutputView {
    fun printInitialCards(players: List<Player>) {
        println("\n${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")

        players.forEach { printCards(it) }
        println()
    }

    fun printCards(player: Player) {
        println("${player.name}카드: ${getPlayerCards(player)}")
    }

    fun printResult(players: List<Player>) {
        println()

        players.forEach {
            println("${it.name}카드: ${getPlayerCards(it)} - 결과: ${it.cards.calculateScore()}")
        }
    }

    private fun getPlayerCards(player: Player): String {
        return player.cards
            .get()
            .joinToString { it.toOutputString() }
    }

    private fun Card.toOutputString(): String {
        return "${this.denomination.alias}${this.suit.toOutputString()}"
    }

    private fun Suit.toOutputString(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }
}
