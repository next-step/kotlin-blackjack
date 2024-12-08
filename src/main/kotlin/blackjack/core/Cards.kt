package blackjack.core

class Cards(private val cards: MutableSet<Card> = mutableSetOf()) : MutableSet<Card> by cards {
    var status: Status = Status.HIT

    fun checkBust() {
        if (point() > MAX_POINT) {
            status = Status.BUSTED
        }
    }

    fun getCardNames(): String = this.joinToString(",", "", "")

    fun point(): Int {
        val sum = cards.sumOf { it.denomination.score }
        val aces = cards.filter { it.denomination == Denomination.ACE }

        if (aces.isEmpty()) {
            return sum
        }

        if (!checkBust(sum, ACE_WEIGHT)) {
            return sum + ACE_WEIGHT
        }

        return sum
    }

    private fun checkBust(
        sum: Int,
        weight: Int,
    ): Boolean = (sum + weight) > MAX_POINT

    companion object {
        const val MAX_POINT = 21
        private const val ACE_WEIGHT = 10
    }
}
