package blackjack.deck

import blackjack.card.Card

data class Cards(
    var cards: List<Card>,
) {
    fun draw() = cards.first()

    fun discard(card: Card) {
        cards = cards.filterNot { it == card }
    }

    fun isNotEmpty() = cards.isNotEmpty()
}
