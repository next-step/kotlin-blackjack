package blackjack.domain.card

class CardTotal(private val cards: List<Card>) {
    val isBusted: Boolean
        get() = getSmallestTotal().isAboveTwentyOne()

    val value: Int
        get() = if (isBusted) getSmallestTotal() else getLargestTotalNotExceedingTwentyOne()

    private fun getBonus(): Int = cards.firstOrNull { it is Card.Ace }?.bonusValue ?: 0

    private fun getSmallestTotal(): Int = cards.sumOf { it.value }

    private fun getLargestTotalNotExceedingTwentyOne(): Int {
        val smallestTotal = getSmallestTotal()
        val bonus = getBonus()

        return (smallestTotal + bonus).takeUnless { it.isAboveTwentyOne() } ?: smallestTotal
    }

    private fun Int.isAboveTwentyOne() = this > TWENTY_ONE

    companion object {
        private const val TWENTY_ONE = 21
    }
}
