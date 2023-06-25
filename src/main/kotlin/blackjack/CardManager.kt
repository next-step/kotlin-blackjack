package blackjack

import blackjack.domain.Card

class CardManager {

    fun getCardsInfo(cards: List<Card>): String {
        var cardsInfo = ""

        cards.forEachIndexed { index, card ->
            cardsInfo += "${card.rank.value}${card.symbol.symbolName}"
            if (index != cards.lastIndex) {
                cardsInfo += ", "
            }
        }
        return cardsInfo
    }
}
