package blackjack.core.player

import blackjack.core.card.Card
import blackjack.core.card.Cards

open class Participant(val name: Name, val cards: Cards = Cards()) {
    val point: Int
        get() = cards.point()

    val cardNum: Int
        get() = cards.size

    fun draw(card: Card) {
        if (checkBust().not()) {
            cards += card
        }
    }

    fun checkBust(): Boolean = cards.checkBust()

    fun checkBlackJack(): Boolean = cards.checkBlackjack()
}
