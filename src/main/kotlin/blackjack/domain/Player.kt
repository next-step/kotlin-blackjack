package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

open class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    override fun toString(): String {
        return name
    }

    open fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun getScore(): Int {
        return cards.getScore()
    }
}
