package blackjack.domain.model

import blackjack.domain.PointCalculator

class Cards(private val cards: MutableList<Card> = mutableListOf(), round: Round) {
    val items: List<Card> get() = cards.toList()
    val sum: Int get() = PointCalculator.sum(this)

    init {
        if (cards.isEmpty()) {
            cards.add(round.getCard())
            cards.add(round.getCard())
        }
    }

    fun add(card: Card) {
        cards.add(card)
    }
}
