package blackjack.domain

import blackjack.domain.card.Card

class Deck(
    cards: List<Card>,
) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    fun drawFirstTurn(): List<Card> {
        return listOf(
            _cards.removeFirst(),
            _cards.removeFirst(),
        )
    }

    companion object {
        fun createOf() = Deck(Card.ALL_CARDS.shuffled())
    }
}
