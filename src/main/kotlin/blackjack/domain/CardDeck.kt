package blackjack.domain

import java.lang.RuntimeException

class CardDeck {
    private val _cards: MutableSet<Card> = CardType.values()
        .flatMap { type -> CardNumber.values().map { number -> Card(type, number) } }
        .toMutableSet()

    val cards: Set<Card>
        get() = _cards

    val count: Int
        get() = _cards.size

    fun draw(): Card {
        if (_cards.isEmpty()) {
            throw RuntimeException("remain card is empty.")
        }

        val takeCard = _cards.shuffled().first()
        _cards.remove(takeCard)
        return takeCard
    }

    override fun toString(): String {
        return cards.map { it.toString() }.joinToString { "\n" }
    }
}
