package blackjack.domain.player

import blackjack.domain.card.Cards

class Dealer(cards: Cards) : Participant(DEALER_NAME, cards) {

    override fun toString(): String {
        return "Dealer(name=$name, cards=$cards)"
    }

    companion object {
        private val DEALER_NAME = Name("딜러")
    }
}
