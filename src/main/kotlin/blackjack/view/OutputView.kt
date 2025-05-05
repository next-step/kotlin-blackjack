package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object OutputView {
    fun printInitialCards(players: Players) {
        println("Dealing two cards to ${players.names.joinToString()}.")
        players.values.forEach { println(it.toView()) }
    }

    fun printPlayerCards(player: Player) = println(player.toView())

    fun printBlackjackResult(players: Players) {
        players.values.forEach {
            println(it.toView() + " - Total: ${it.score ?: "BUST"}")
        }
    }

    fun announceBust(player: Player) {
        println("${player.name.value} busts!")
    }

    private fun Player.toView() = "${this.name.value}'s cards: ${this.cards.toView()}"

    private fun List<Card>.toView() = joinToString(" ") { card -> card.toView() }

    private fun Card.toView() = "${this.number.toView()}${this.suit.toView()}"

    private fun CardNumber.toView() =
        when (this) {
            CardNumber.ACE -> "A"
            CardNumber.TWO -> "2"
            CardNumber.THREE -> "3"
            CardNumber.FOUR -> "4"
            CardNumber.FIVE -> "5"
            CardNumber.SIX -> "6"
            CardNumber.SEVEN -> "7"
            CardNumber.EIGHT -> "8"
            CardNumber.NINE -> "9"
            CardNumber.TEN -> "10"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
        }

    private fun Suit.toView() =
        when (this) {
            Suit.SPADES -> "♠️"
            Suit.DIAMONDS -> "♦️"
            Suit.HEARTS -> "❤️"
            Suit.CLUBS -> "♣️️"
        }
}
