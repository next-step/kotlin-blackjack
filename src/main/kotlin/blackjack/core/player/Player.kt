package blackjack.core.player

import blackjack.core.card.Card
import blackjack.core.card.Cards

open class Player(val name: Name, val cards: Cards = Cards()) {
    fun draw(card: Card): Boolean {
        if (checkBust()) {
            return false
        }
        cards += card

        return true
    }

    fun point(): Int = cards.point()

    fun checkBust(): Boolean = cards.checkBust()
}
