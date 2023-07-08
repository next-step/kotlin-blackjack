package baclkjack.domain.play

import baclkjack.domain.card.Cards
import baclkjack.domain.card.Deck

interface User {

    val name: String

    val cards: Cards

    fun start(deck: Deck) {
        repeat(Cards.FIRST_DRAW) {
            cards.add(deck.draw())
        }
    }

    fun hit(deck: Deck) {
        cards.add(deck.draw())
    }

    fun burst(): Boolean = cards.isBurst

    fun blackJack(): Boolean = cards.isBlackJack

    fun winNumber(): Boolean = cards.isWinningNumber

    fun score(): Int = cards.score()

    fun isDraw(): Boolean
}

interface CardDrawListener {
    fun isDraw(name: String): Boolean
}
