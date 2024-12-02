package blackjack.deck

import blackjack.card.Card

data class Cards(
    var cards: List<Card>,
) {
    fun draw(drawCard: Card): Cards? =
        Cards(cards = cards - drawCard)
            .takeIf { cards.contains(drawCard) }

    fun isNotEmpty() = cards.isNotEmpty()
}
