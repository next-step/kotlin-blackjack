package blackject.model

import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.Cards

/**
 * 참가자 정보 관리 클래스
 * */
open class Person(
    val type: PersonType,
    val name: String,
    val cards: Cards = Cards()
) {
    open fun isTakeMoreCard(maxInt: Int, dealerMaxNumber: Int, exceptCard: CardNumber): Boolean {
        if (type == PersonType.DEALER) {
            return dealerMaxNumber >= cards.getResultNumber(maxInt, exceptCard)
        }
        return maxInt > cards.getResultNumber(maxInt, exceptCard)
    }

    open fun giveCard(newCards: List<Card>) {
        cards.addCard(newCards)
    }
}
