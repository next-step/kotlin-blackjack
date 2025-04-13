package blackjack.domain.deck

import blackjack.domain.card.Card

class Deck private constructor(
    private val cards: List<Card>,
) {
    init {
        check(cards.size == cards.distinct().size) {
            "Deck must not contain duplicated cards."
        }
    }

    companion object {
        fun create(generator: () -> List<Card> = RandomDeckGenerator::generate) = Deck(generator())
    }
}
