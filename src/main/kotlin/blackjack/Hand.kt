package blackjack

class Hand(initialCards: List<Card> = emptyList()) {
    private val _cards = initialCards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun add(newCard: Card) {
        _cards.add(newCard)
    }

    fun sumOfHand(): Int {
        val nonAceSum = sumNonAce()
        val aceCount = _cards.count { it.number == CardNumber.ACE }
        if (aceCount == 0) {
            return nonAceSum
        }
        return calculateTotalSumWithAceSum(nonAceSum, aceCount)
    }

    private fun sumNonAce(): Int =
        _cards
            .asSequence()
            .filter { it.number != CardNumber.ACE }
            .sumOf { it.number.baseValue }

    private fun calculateTotalSumWithAceSum(
        nonAceSum: Int,
        aceCount: Int,
    ): Int {
        val baseAceSum = aceCount * CardNumber.ACE.baseValue
        val biggerAceSum = (aceCount - 1) * CardNumber.ACE.baseValue + CardNumber.ACE.biggerValue()

        val baseSum = nonAceSum + baseAceSum
        val biggerSum = nonAceSum + biggerAceSum

        return if (biggerSum <= BLACKJACK_NUMBER) biggerSum else baseSum
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
