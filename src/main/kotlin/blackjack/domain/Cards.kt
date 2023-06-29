package blackjack.domain

import blackjack.enums.Rank

class Cards(
    cards: List<Card>
) {

    private val mutableCards = cards.toMutableList()

    val cards: List<Card>
        get() = mutableCards

    fun append(card: Card) {
        mutableCards.add(card)
    }

    fun pick(): Card {
        return cards.first()
    }

    fun calculateScore(): Score {

        var total = cards.sumOf { it.rank.value }

        if (cards.any { it.rank == Rank.ACE } && total - Rank.ACE.value <= STANDARD_NUMBER) {
            total += -Rank.ACE.value + Rank.ACE.hiddenValue
        }
        return Score(total)
    }

    companion object {
        private const val STANDARD_NUMBER = 10
    }
}
