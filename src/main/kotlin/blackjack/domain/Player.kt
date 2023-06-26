package blackjack.domain

import blackjack.domain.card.Card

data class Player(
    val name: String,
    var cards: List<Card> = emptyList()
) {
    override fun toString(): String {
        return name
    }
}
