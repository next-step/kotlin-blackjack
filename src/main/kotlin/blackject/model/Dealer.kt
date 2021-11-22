package blackject.model

import blackject.model.card.CardNumber
import blackject.model.card.Cards

class Dealer(
    cards: Cards = Cards()
) : Person(NAME, cards) {

    override fun isPersonType(): PersonType = PersonType.DEALER

    fun canTakeMoreCard(exceptCard: CardNumber): Boolean {
        return MAX_NUMBER_DEALER >= cards.getResultNumber(MAX_NUMBER_DEALER, exceptCard)
    }

    fun isOverMaxInt(maxInt: Int, exceptCard: CardNumber): Boolean = getScore(maxInt, exceptCard) > maxInt

    companion object {
        const val MAX_NUMBER_DEALER = 16
        const val NAME = "딜러"
    }
}
