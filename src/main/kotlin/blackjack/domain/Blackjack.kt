package blackjack.domain

import blackjack.domain.Denomination.ACE
import blackjack.domain.Denomination.JACK
import blackjack.domain.Denomination.KING
import blackjack.domain.Denomination.QUEEN

class Blackjack(
    val players: List<Player>
) {
    private val deck = Deck.create()

    init {
        deck.shuffle()
    }

    fun drawingCard(player: Player): Card {
        require(player in players) { "[${player.name} is not blackjack player" }
        val card = deck.draw()
        player.addCard(deck.draw())

        return card
    }

    fun drawingCards(player: Player, n: Int): List<Card> {
        require(player in players) { "[${player.name} is not blackjack player" }

        val cards = deck.draw(n)
        player.addCards(cards)

        return cards
    }

    fun calculatePoints(player: Player): Int {
        require(player in players) { "[${player.name} is not blackjack player" }

        var points = 0
        player.cards.sortedBy { it.denomination.ordinal }.forEach {
            points += when (it.denomination) {
                ACE -> getAcePoints(points)
                JACK, QUEEN, KING -> 10
                else -> it.denomination.value.toInt()
            }
        }

        return points
    }

    private fun getAcePoints(points: Int): Int {
        return if (points <= 10) 11
        else 1
    }
}
