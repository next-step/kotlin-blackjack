package blackJack.domain.player

import blackJack.domain.card.Card
import blackJack.domain.card.Cards
import blackJack.domain.enums.Status

open class Participant(var status: Status = Status.HIT, var cards: Cards = Cards(emptyList())) {

    fun getTotalScore(): Int = cards.calculateTotalScore()

    open fun isContinued() = status == Status.HIT

    fun isBlackJack(): Boolean = status == Status.BLACKJACK

    fun addCard(card: Card) {
        Status.validationAddCard(status)
        cards.addCard(card)
        status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
    }

    fun receiveInitialCards(initialCards: Cards) {
        cards.addAllCard(initialCards)
        status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
    }
}
