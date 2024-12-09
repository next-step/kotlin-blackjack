package blackjack.core.card

class Cards(private val cards: MutableSet<Card> = mutableSetOf()) : MutableSet<Card> by cards {
    fun checkBust(): Boolean {
        return point() > MAX_POINT
    }

    fun point(): Int {
        val sum = cards.sumOf { it.denomination.score }
        val aces = cards.filter { it.denomination == Denomination.ACE }
        if (aces.isEmpty()) {
            return sum
        }
        if (!checkAceBust(sum)) {
            return sum + ACE_WEIGHT
        }
        return sum
    }

    private fun checkAceBust(sum: Int): Boolean = (sum + ACE_WEIGHT) > MAX_POINT

    companion object {
        const val MAX_POINT = 21
        private const val ACE_WEIGHT = 10
    }
}
