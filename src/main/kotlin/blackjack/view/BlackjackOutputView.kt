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
        val playerCards = player.cards
            .get()
            .joinToString { it.toOutputString() }

        println("${player.name}카드: $playerCards")
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
