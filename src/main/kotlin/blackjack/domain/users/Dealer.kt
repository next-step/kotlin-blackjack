package blackjack.domain.users

import blackjack.domain.card.Cards

class Dealer(
    name: String,
    cards: Cards,
) : User(name, cards) {
    fun cardReceivePossible(): Boolean {
        return cards.value() <= DEALER_CARD_MUST_RECEIVE_COUNT
    }

    companion object {
        const val DEALER_CARD_MUST_RECEIVE_COUNT = 16
    }
}
