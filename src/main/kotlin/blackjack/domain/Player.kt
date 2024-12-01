package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

data class Player(
    val name: String,
    val cards: Cards = Cards(),
) {
    fun receivedCard(card: Card) {
        cards.add(card)
    }
}
