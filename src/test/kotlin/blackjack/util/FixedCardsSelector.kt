package blackjack.util

import blackjack.domain.Card

class FixedCardsSelector(vararg cardList: Card) : CardSelector {
    private val fixedCards = cardList.toMutableList()
    override fun drawCard(): Card {
        return fixedCards.removeAt(0)
    }
}
