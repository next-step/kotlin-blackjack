package blackjack.domain.card

class CardHold private constructor(
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
            card.getPoints().max()
        }
    }

    private fun adjustAceSum(sum: Int): Int {
        var calibratedSum = sum
        var aceCardSize = cards.count { it.rank == CardRank.ACE }

        while (0 < aceCardSize && calibratedSum > THRESHOLD) {
            calibratedSum -= 10
            aceCardSize--
        }

        return calibratedSum
    }

    override fun toString(): String = cards.joinToString(", ") { it.toString() }

    companion object {
        const val THRESHOLD: Int = 21
        fun init(): CardHold = CardHold()
    }
}
