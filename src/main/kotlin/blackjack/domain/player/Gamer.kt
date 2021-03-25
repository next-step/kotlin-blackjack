package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards

abstract class Gamer(val name: String, val cards: Cards) {

    init {
        require(name.isNotBlank())
    }

    abstract fun isNotTakeable(): Boolean

    fun takeCard(card: Card) {
        cards.add(card)
    }
}
