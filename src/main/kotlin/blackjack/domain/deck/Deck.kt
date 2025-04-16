package blackjack.domain.deck

import blackjack.domain.card.Card

class Deck private constructor(
    private val cards: MutableList<Card>,
) {
    init {
        check(cards.size == cards.distinct().size) {
            "Deck must not contain duplicated cards."
        }
    }

    fun drawCard(): Card {
        check(cards.isNotEmpty()) { "There are no cards left in deck." }
        return cards.removeFirst()
    }

    companion object {
        fun create(generator: () -> MutableList<Card> = RandomDeckGenerator::generate) = Deck(generator())
    }
}
