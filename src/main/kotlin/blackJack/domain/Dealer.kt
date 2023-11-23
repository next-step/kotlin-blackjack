package blackJack.domain

import blackJack.error.ErrorMessage

class Dealer(val cardDeck: Cards) {

    init {
        require(cardDeck.cardSize == 52) { ErrorMessage.CARD_DECK_SIZE.message }
    }

    fun initialCards(): Cards {
        return Cards(MutableList(INIT_CARD_COUNT) { cardDeck.drawCard() })
    }

    companion object {
        const val INIT_CARD_COUNT = 2
    }
}
