package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.player.Players

object OutputView {
    fun printInitialCards(players: Players) {
        println("Dealing two cards to ${players.names.joinToString()}.")
        players.values.forEach {
            println("${it.name.value}'s cards: ${it.cards.toView()}.")
        }
    }

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
