package blackjack.domain

class Cards(
    cards: List<Card>
) {

    private val mutableCards = cards.toMutableList()

    private val cards: List<Card>
        get() = mutableCards

    fun append(card: Card) {
        mutableCards.add(card)
    }

    fun pick(): Card {
        return cards.first()
    }

    fun extractCardsInfoAsString(): String {
        var cardsInfo = ""

        cards.forEachIndexed { index, card ->
            cardsInfo += "${card.rank.value}${card.symbol.symbolName}"
            if (index != cards.lastIndex) {
                cardsInfo += ", "
            }
        }
        return cardsInfo
    }

    fun calculateCardsTotalValue(): Int {
        var total = 0
        cards.filter { it.rank.rank != ACE_RANK_MARK }.forEach { card ->
            total += card.rank.value
        }

        val rankACards = cards.filter { it.rank.rank == ACE_RANK_MARK }
        if (rankACards.isNotEmpty()) {
            rankACards.forEach { card ->
                total += if (total > STANDARD_NUMBER) {
                    card.rank.value
                } else {
                    card.rank.hiddenValue
                }
            }
        }
        return total
    }

    companion object {
        private const val ACE_RANK_MARK = "A"
        private const val STANDARD_NUMBER = 10
    }
}
