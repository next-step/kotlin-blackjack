package blackjack.domain.deck

import java.util.LinkedList
import java.util.Queue

class Deck(
    val cards: Queue<Card>,
) {
    companion object {
        fun release(): Deck = Deck(
            CardPattern.values()
                .flatMap { createCardWith(pattern = it) }
                .toCollection(LinkedList())
        )

        private fun createCardWith(pattern: CardPattern): List<Card> =
            CardNumber.values()
                .map { Card(pattern = pattern, number = it) }
    }
}
