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
    val cards: Cards = Cards(),
    var result: ResultType? = null
) {

    open fun getScore(maxInt: Int, exceptCard: CardNumber): Int {
        return cards.getResultNumber(maxInt, exceptCard)
    }

    open fun isTakeMoreCard(maxInt: Int, exceptCard: CardNumber): Boolean {
        return maxInt > cards.getResultNumber(maxInt, exceptCard)
    }

    open fun giveCard(newCards: List<Card>) {
        cards.addCard(newCards)
    }

    open fun changeResultType(result: ResultType) {
        this.result = result
    }

    open fun hasResult(): Boolean = result != null
}
