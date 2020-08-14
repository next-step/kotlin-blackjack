package blackjack

import java.util.LinkedList
import java.util.Queue

object Deck {
    const val TOTAL_DECK_SIZE = 52

    val cards: Queue<Card> = LinkedList<Card>(
        accumulateCards()
            .flatten()
            .shuffled()
    )

    private fun accumulateCards(): List<List<Card>> =
        Denomination.values()
            .map(::setupCard)

    private fun setupCard(denomination: Denomination): List<Card> =
        Shape.values().map { shape ->
            Card(denomination to shape)
        }
}
