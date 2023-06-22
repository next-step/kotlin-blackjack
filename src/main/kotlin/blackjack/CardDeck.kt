package blackjack

import java.util.LinkedList
import java.util.Queue

@JvmInline
value class CardDeck(
    private val values: Queue<Card> = LinkedList(),
) {
    fun size() = values.size

    fun deal() {
        check(values.isNotEmpty()) { "남은 카드가 없을 땐 deal할 수 없다" }
    }

    companion object {
        private val INIT_CARD_DECK =
            Suit.values().flatMap { suit -> Denomination.values().map { denomination -> Card(suit, denomination) } }

        fun randomCardDeck() = CardDeck(
            INIT_CARD_DECK.shuffled().toCollection(LinkedList()),
        )
    }
}
