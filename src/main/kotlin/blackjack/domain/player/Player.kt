package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Player(val name: String, val cards: Cards = Cards()) {
    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun canReceiveCard(): Boolean = cards.sum() >= Cards.MAX_CARDS_NUM_SUM
}
