package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

open class Player(val name: String) {
    val cards: Cards = Cards()

    fun addCardToHand(card: Card) {
        this.cards += card
    }
}
