package blackjack.domain.card

class CardHold(
    val cards: List<Card> = emptyList()
) {
    fun add(newCard: Card): CardHold {
        val addedCards = cards + newCard
        return CardHold(addedCards)
    }

    fun getPoints(): Int {
        val sum = getTotalPoints()
        return adjustAceSum(sum)
    }

    private fun getTotalPoints(): Int {
        return cards.sumOf { card ->
            card.getPoint()
        }
    }

    private fun adjustAceSum(sum: Int): Int {
        if (cards.any { card -> card.rank == CardRank.ACE }) {
            return sum + CardRank.ACE.point[1]
        }
        return sum
    }

    companion object {
        const val THRESHOLD: Int = 21
    }
}
