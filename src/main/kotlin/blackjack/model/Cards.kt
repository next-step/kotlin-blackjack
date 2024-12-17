package blackjack.model

import kotlin.math.min

class Cards(private val deck: Deck, cards: Set<Card> = setOf()) {
    private val _cards = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun addNewCard() {
        _cards.add(deck.takeRandomCard())
    }

    fun calculateCardValues(): Int {
        val total = _cards.sumOf { it.getValue() }
        val aceCount = _cards.count { it.isAce }
        val extraAces = min(aceCount, (WINNING_NUMBER - total) / ACE_VALUE)
        return total + extraAces * ACE_VALUE
    }

    override fun toString(): String {
        return _cards.toString()
    }

    companion object {
        const val WINNING_NUMBER = 21
        const val ACE_VALUE = 10
    }
}
