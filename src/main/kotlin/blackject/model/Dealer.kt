package blackject.model

import blackject.model.card.CardNumber
import blackject.model.card.Cards

class Dealer(
    cards: Cards = Cards()
) : Person(PersonType.DEALER, NAME, cards) {

    override fun isTakeMoreCard(maxInt: Int, exceptCard: CardNumber): Boolean {
        return maxInt >= cards.getResultNumber(maxInt, exceptCard)
    }

    companion object {
        const val NAME = "딜러"
    }
}
