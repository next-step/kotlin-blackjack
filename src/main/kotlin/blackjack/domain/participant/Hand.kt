package blackjack.domain.participant

import blackjack.domain.card.Card

data class Hand(
    private val _cards: MutableList<Card> = mutableListOf()
) {
    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card): Hand {
        _cards.add(card)
        return this
    }

    fun score(): Int {
        return _cards
            .sortedBy { it.symbol.value }
            .fold(0) { acc, card -> acc + card.count(acc) }
    }

    fun isBust(): Boolean {
        return score() > BUST_THRESHOLD
    }

    companion object {
        const val BUST_THRESHOLD: Int = 21
    }
}
