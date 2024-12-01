package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

sealed class Participant(
    val name: String,
    val cards: Cards = Cards(),
) {
    fun receiveCard(card: Card) {
        cards.add(card)
    }

    abstract fun canReceive(): Boolean
}
