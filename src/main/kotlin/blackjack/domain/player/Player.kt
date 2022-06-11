package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Player(val name: String) {
    val cards: Cards = Cards()

    fun addCard(card: Card) {
        this.cards += card
    }
}
