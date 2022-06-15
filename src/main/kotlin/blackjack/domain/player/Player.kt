package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

open class Player(val name: String) {
    open val isFirstCardHidden: Boolean = false

    val cards: Cards = Cards()

    fun addCardToHand(card: Card) {
        this.cards += card
    }
}
