package blackject.model

import blackject.model.card.Card
import blackject.model.card.Cards
import blackject.model.card.CardsDeck

/**
 * 참가자 정보 관리 클래스
 * */
open class Person(
    val name: String,
    val cards: Cards = Cards(),
) : Player() {
    open fun getScore(): Int {
        return cards.getResultNumber()
    }

    open fun canTakeMoreCard(): Boolean {
        return Cards.BLACK_JACK_SUM > cards.getResultNumber()
    }

    open fun giveCard(newCards: List<Card>) {
        cards.addCard(newCards)
    }

    open fun isPersonType(): PersonType = PersonType.NORMAL

    fun giveCards(cardCount: Int, print: (Person) -> Unit) {
        giveCard(CardsDeck.takeCard(cardCount))
        print.invoke(this)
    }
}
