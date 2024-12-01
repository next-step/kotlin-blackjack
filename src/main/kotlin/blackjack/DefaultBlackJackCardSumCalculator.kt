package blackjack

class DefaultBlackJackCardSumCalculator : BlackJackCardSumCalculator {
    override fun sum(cards: List<Card>): Int {
        val sumNonAce =
            cards.asSequence()
                .filterNot { it.number == CardNumber.Ace }
                .sumOf { it.number.baseValue }
        val aceCount = cards.count { it.number == CardNumber.Ace }

        val baseSum = sumNonAce + (aceCount - 1) * CardNumber.Ace.baseValue

        return if ((baseSum + ACE_BIGGER_NUMBER) <= BLACKJACK_NUMBER) {
            baseSum + ACE_BIGGER_NUMBER
        } else {
            baseSum + CardNumber.Ace.baseValue
        }
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ACE_BIGGER_NUMBER = 11
    }
}
