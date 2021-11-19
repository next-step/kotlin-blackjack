package blackject.model

import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.Cards

/**
 * 참가자 정보 관리 클래스
 * */
data class Person(
    val name: String,
    val cards: Cards = Cards()
) {
    fun isTakeMoreCard(maxInt: Int, exceptCard: CardNumber): Boolean {
        return maxInt > cards.getResultNumber(maxInt, exceptCard)
    }

    fun giveCard(newCards: List<Card>) {
        cards.addCard(newCards)
    }
}
