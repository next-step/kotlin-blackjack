package blackJack.domain

import blackJack.error.ErrorMessage
import java.util.LinkedList
import java.util.Queue

class Dealer(val cardDeck: Cards) {

    init {
        require(cardDeck.cardSize == 52) { ErrorMessage.CARD_DECK_SIZE.message }
    }

    fun initialCards(): Cards {
        return Cards(MutableList(INIT_CARD_COUNT) { cardDeck.drawCard() })
    }

    fun drawCard(): Card = cardDeck.drawCard()

    companion object {
        const val INIT_CARD_COUNT = 2
    }
}
