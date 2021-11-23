package blackject.model

import blackject.model.card.Cards

class Dealer(
    cards: Cards = Cards()
) : Person(NAME, cards) {

    override fun isPersonType(): PersonType = PersonType.DEALER

    override fun canTakeMoreCard(): Boolean {
        return MAX_NUMBER_DEALER >= cards.getResultNumber() // TODO 확인하기
    }

    fun isOverMaxInt(): Boolean = getScore() > Cards.BLACK_JACK_SUM

    companion object {
        const val MAX_NUMBER_DEALER = 16
        const val NAME = "딜러"
    }
}
