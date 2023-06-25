package blackjack

import blackjack.domain.Card
import blackjack.enums.Rank

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

    fun getCardsTotalValue(cards: List<Card>): Int {
        var total = 0
        cards.filter { it.rank.rank != "A" }.forEach { card ->
            total += card.rank.value
        }

        val rankACards = cards.filter { it.rank.rank == "A" }
        if(rankACards.isNotEmpty()) {
            rankACards.forEach { card ->
                total += if(total > STANDARD_NUMBER) {
                    card.rank.value
                } else {
                    Rank.SECRET_ACE_MAX_VALUE
                }
            }
        }
        return total
    }

    companion object {
        private const val STANDARD_NUMBER = 10
    }
}
