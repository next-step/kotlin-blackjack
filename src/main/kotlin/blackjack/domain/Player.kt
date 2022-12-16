package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

sealed class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScore(): Int {
        return cards.calculate()
    }
}
