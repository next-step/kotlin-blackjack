package domain.player

import domain.card.Card
import domain.card.Cards

open class Player(val name: String, val cards: Cards = Cards()) {

    fun hit(card: Card) {
        cards.add(card)
    }

    fun result(): Int {
        return cards.result()
    }

    open fun canReceiveMoreCard(): Boolean {
        return result() < Cards.BLACKJACK_POINT
    }
}
