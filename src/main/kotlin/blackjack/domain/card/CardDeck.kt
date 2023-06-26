package blackjack.domain.card

import java.util.LinkedList
import java.util.Queue

@JvmInline
value class CardDeck(
    private val values: Queue<Card> = LinkedList(),
) {
    fun size(): Int = values.size

    fun draw(): Card {
        check(values.isNotEmpty()) { "남은 카드가 없을 땐 드로우할 수 없다" }
        return values.poll()
    }

    companion object {
        private val INIT_CARD_DECK =
            Suit.values().flatMap { suit -> Denomination.values().map { denomination -> Card(suit, denomination) } }

        fun randomCardDeck() = CardDeck(INIT_CARD_DECK.shuffled().toCollection(LinkedList()))
    }
}
