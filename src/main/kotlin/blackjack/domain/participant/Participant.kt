package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Participant(
    val name: String,
    val cards: Cards = Cards(),
) {

    fun receivedCard(card: Card) {
        cards.add(card)
    }

}
