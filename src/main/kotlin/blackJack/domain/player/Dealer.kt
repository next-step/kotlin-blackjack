package blackJack.domain.player

import blackJack.domain.card.Card
import blackJack.domain.card.Cards
import blackJack.domain.enums.Status
import blackJack.domain.enums.Status.*

class Dealer(val name: String, val cards: Cards = Cards(emptyList()), var status: Status = HIT) {

    fun receiveInitialCards(initialCards: Cards) {
        cards.addAllCard(initialCards)
        status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
    }

    fun isContinued(totalScore: Int) = status == HIT && totalScore < 17

    fun addCard(card: Card) {
        Status.validationAddCard(status)
        cards.addCard(card)
        status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
    }

    companion object {
        fun createDealer(): Dealer {
            return Dealer(DEALER_NAME)
        }

        private const val DEALER_NAME = "딜러"
    }
}
