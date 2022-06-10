package blackjack.domain

import blackjack.domain.interfaces.CardFactory

class Dealer() {
    fun give(cardFactory: CardFactory): Card {
        return cardFactory.create()
    }

    companion object {
        const val BASIC_CARD_AMOUNT = 2
    }
}
