package game.blackjack.domain

import java.util.*

class Deck {
    private val _cards: Stack<Card> = Stack()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        val cards: MutableList<Card> = mutableListOf()
        Denomination.values().forEach { rank ->
            Suit.values().forEach { cards.add(Card(it, rank)) }
        }

        _cards.addAll(cards.shuffled())
    }

    fun takeCard(): Card {
        return _cards.pop()
    }

}
