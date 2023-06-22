package blackjack

import java.util.LinkedList
import java.util.Queue

@JvmInline
value class CardDeck(
    private val values: Queue<Card>,
) {
    fun size() = values.size

    companion object {
        private val INIT_CARD_DECK =
            Suit.values().flatMap { suit -> Denomination.values().map { denomination -> Card(suit, denomination) } }

        fun randomCardDeck() = CardDeck(
            INIT_CARD_DECK.shuffled().toCollection(LinkedList()),
        )
    }
}
