package blackjack.domain.deck

import blackjack.domain.card.Card

class Deck(
    val cards: ArrayDeque<Card>,
) {
    fun draw(): Card = cards.removeFirst()
}
