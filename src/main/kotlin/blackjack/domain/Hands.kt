package blackjack.domain

class Hands {
    private val cards: MutableList<Card> = mutableListOf()

    val size: Int
        get() = cards.size

    fun add(card: Card) {
        cards.add(card)
    }

    fun calculateTotalValue(): Int {
        val nonAceValue = sumOfNonAceValue()
        val aceCount = countAces()
        val totalValue = sumTotalValueWithAce(nonAceValue, aceCount)

        return totalValue
    }

    private fun countAces(): Int = cards.count { it.rank.isAce() }

    private fun sumOfNonAceValue(): Int =
        cards.filterNot { it.rank.isAce() }
            .sumOf(Card::value)

    private fun sumTotalValueWithAce(
        totalValue: Int,
        aceCount: Int,
    ): Int {
        return if (canUseAceAlternativeValue(aceCount, totalValue)) {
            totalValue + ACE_ALTERNATIVE_VALUE + (aceCount - 1)
        } else {
            totalValue + aceCount
        }
    }

    private fun canUseAceAlternativeValue(
        aceCount: Int,
        totalValue: Int,
    ): Boolean = aceCount > 0 && totalValue + ACE_ALTERNATIVE_VALUE + (aceCount - 1) <= BLACKJACK_VALUE

    override fun toString(): String {
        return cards.joinToString(", ")
    }
}
