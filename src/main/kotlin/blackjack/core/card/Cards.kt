package blackjack.core.card

class Cards(private val cards: MutableSet<Card> = mutableSetOf()) : MutableSet<Card> by cards {
    fun checkBust(): Boolean {
        return point() > BLACKJACK_POINT
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

    fun checkBlackjack(): Boolean {
        return point() == BLACKJACK_POINT && size == BLACKJACK_CARD_NUM
    }

    private fun checkAceBust(sum: Int): Boolean = (sum + ACE_WEIGHT) > BLACKJACK_POINT

    companion object {
        const val BLACKJACK_POINT = 21
        const val BLACKJACK_CARD_NUM = 2
        private const val ACE_WEIGHT = 10
    }
}
