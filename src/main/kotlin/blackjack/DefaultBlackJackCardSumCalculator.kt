package blackjack

class DefaultBlackJackCardSumCalculator : BlackJackCardSumCalculator {
    override fun sum(cards: List<Card>): Int {
        val (aces, nonAces) = cards.partition { it.denomination == Denomination.ACE }
        val nonAceSum = nonAces.sumOf { it.number() }
        val aceCount = aces.size

        return when {
            aceCount == 0 -> nonAceSum
            else -> calculateBestSumWithAces(nonAceSum, aceCount)
        }
    }

    private fun calculateBestSumWithAces(
        sumNonAce: Int,
        aceCount: Int,
    ): Int {
        val possibleSums =
            (0..aceCount).map { aceAsElevenCount ->
                sumNonAce + (aceAsElevenCount * ACE_BIGGER_NUMBER) + (aceCount - aceAsElevenCount) * Denomination.ACE.score
            }

        return possibleSums.filter { it <= BLACKJACK_NUMBER }.maxOrNull() ?: possibleSums.minOf { it }
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ACE_BIGGER_NUMBER = 11
    }
}
