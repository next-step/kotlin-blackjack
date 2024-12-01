package blackjack.domain

class Cards private constructor(
    private val cards: MutableList<Card> = mutableListOf(),
) {
    val size: Int
        get() = cards.size

    fun add(card: Card) {
        cards.add(card)
    }

    fun draw(): Card {
        val card = cards.first()
        cards.remove(card)
        return card
    }

    fun calculateTotalValue(): Int {
        val nonAceValue = sumOfNonAceValue()
        val aceCount = countAces()
        val totalValue = sumTotalValueWithAce(nonAceValue, aceCount)

        return totalValue
    }

    private fun countAces(): Int = cards.count { it.rank == Rank.ACE }

    private fun sumOfNonAceValue(): Int =
        cards.filter { it.rank != Rank.ACE }
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
    ): Boolean = aceCount > 0 && totalValue + ACE_ALTERNATIVE_VALUE + (aceCount - 1) <= BLACKJACK

    override fun toString(): String {
        return cards.joinToString(", ")
    }

    companion object {
        private const val BLACKJACK = 21
        private const val ACE_ALTERNATIVE_VALUE = 11

        fun emptyCards(): Cards {
            return Cards()
        }

        fun fullCards(): Cards {
            val cards = Cards()
            setup(cards)
            return cards
        }

        private fun setup(cards: Cards) {
            Suit.entries.forEach { suit ->
                addCardsForSuit(cards, suit)
            }
            shuffle(cards)
        }

        private fun addCardsForSuit(
            cards: Cards,
            suit: Suit,
        ) {
            Rank.entries.forEach { rank ->
                cards.add(Card(suit, rank))
            }
        }

        private fun shuffle(cards: Cards) {
            cards.cards.shuffle()
        }
    }
}
