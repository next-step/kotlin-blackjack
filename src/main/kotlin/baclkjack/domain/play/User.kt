package baclkjack.domain.play

import baclkjack.domain.card.Cards
import baclkjack.domain.card.Deck

interface User {

    val name: String

    val cards: Cards

    val money: Money

    fun start(deck: Deck)

    fun hit(deck: Deck)

    fun burst(): Boolean

    fun winNumber(): Boolean

    fun blackJack(): Boolean

    fun score(): Int

    fun isDraw(): Boolean
}

interface CardDrawListener {
    fun isDraw(name: String): Boolean
}
