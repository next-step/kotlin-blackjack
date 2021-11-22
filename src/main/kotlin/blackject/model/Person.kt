package blackject.model

import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.Cards
import blackject.model.card.CardsDeck

/**
 * 참가자 정보 관리 클래스
 * */
open class Person(
    val name: String,
    val cards: Cards = Cards(),
) {
    var result: ResultType? = null

    open fun getScore(maxInt: Int, exceptCard: CardNumber): Int {
        return cards.getResultNumber(maxInt, exceptCard)
    }

    open fun canTakeMoreCard(maxInt: Int, exceptCard: CardNumber): Boolean {
        return maxInt > cards.getResultNumber(maxInt, exceptCard)
    }

    open fun giveCard(newCards: List<Card>) {
        cards.addCard(newCards)
    }

    open fun changeResultType(result: ResultType) {
        this.result = result
    }

    open fun hasResult(): Boolean = result != null

    open fun isPersonType(): PersonType = PersonType.NORMAL

    fun giveCards(cardCount: Int, print: (Person) -> Unit) {
        giveCard(CardsDeck.takeCard(cardCount))
        print.invoke(this)
    }
}
