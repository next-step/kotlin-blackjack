package blackjack

class DefaultBlackJackCardSumCalculator : BlackJackCardSumCalculator {
    override fun sum(cards: List<Card>): Int {
        val sumNonAce = sumNonAceValue(cards)
        val aceCount = cards.count { it.number == CardNumber.Ace }

        if (aceCount == 0) {
            return sumNonAce
        }

        val baseSum = getSumWithoutLastAce(sumNonAce, aceCount)
        return baseSum + getBestAceValue(baseSum)
    }

    private fun sumNonAceValue(cards: List<Card>): Int =
        cards
            .asSequence()
            .filterNot { it.number == CardNumber.Ace }
            .sumOf { it.number.baseValue }

    private fun getSumWithoutLastAce(
        sumNonAce: Int,
        aceCount: Int,
    ) = sumNonAce + (aceCount - 1) * CardNumber.Ace.baseValue

    private fun getBestAceValue(baseSum: Int) =
        if (baseSum + ACE_BIGGER_NUMBER <= BLACKJACK_NUMBER) {
            ACE_BIGGER_NUMBER
        } else {
            CardNumber.Ace.baseValue
        }

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ACE_BIGGER_NUMBER = 11
    }
}
