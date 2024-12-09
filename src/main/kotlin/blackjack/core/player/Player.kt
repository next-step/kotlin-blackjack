package blackjack.core.player

import blackjack.core.card.Card
import blackjack.core.card.Cards

open class Player(val name: Name, val cards: Cards = Cards()) {
    fun draw(card: Card) {
        if (checkBust().not()) {
            cards += card
        }
    }

    val point: Int
        get() = cards.point()

    fun checkBust(): Boolean = cards.checkBust()
}
