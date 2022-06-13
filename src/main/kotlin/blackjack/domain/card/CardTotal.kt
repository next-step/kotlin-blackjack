package blackjack.domain.card

class CardTotal(private val cards: List<Card>) {
    val isBusted: Boolean
        get() = getSmallestTotal().isAboveTwentyOne()

    val value: Int
        get() = if (isBusted) getSmallestTotal() else getLargestTotalNotExceedingTwentyOne()

    private fun getAllNonZeroBonuses(): List<Int> = cards
        .map { it.bonusValue }
        .filter { it > 0 }

    private fun getSmallestTotal(): Int = cards.sumOf { it.value }

    private fun getLargestTotalNotExceedingTwentyOne(): Int {
        val smallestTotal = getSmallestTotal()
        val bonuses = getAllNonZeroBonuses()

        var total = smallestTotal

        bonuses.forEach { bonus ->
            if ((total + bonus).isAboveTwentyOne()) return@forEach
            total += bonus
        }

        return total
    }

    private fun Int.isAboveTwentyOne() = this > TWENTY_ONE

    companion object {
        private const val TWENTY_ONE = 21
    }
}
