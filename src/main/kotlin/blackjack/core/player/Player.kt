package blackjack.core.player

import blackjack.core.card.Card
import blackjack.core.card.Cards

open class Player(val name: Name, val cards: Cards = Cards()) {
    fun draw(card: Card) {
        cards += card
    }

    fun point(): Int = cards.point()

    fun getCardNames(): String = cards.getCardNames()

    fun checkBust(): Boolean = cards.checkBust()
}
