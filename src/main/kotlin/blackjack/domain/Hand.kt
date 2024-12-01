package blackjack.domain

class Hand(
    cards: List<Card> = emptyList(),
) {
    private val cards = mutableListOf<Card>()

    init {
        this.cards.addAll(cards)
    }

    operator fun get(index: Int): Card = cards[index]

    fun value(): Int {
        val handValue = cards.sumOf { it.rankValue }
        if (isAceExtraValue(handValue)) {
            return handValue + ACE_EXTRA_VALUE
        }
        return handValue
    }

    fun drawFrom(deck: Deck) {
        cards.add(deck.draw())
    }

    private fun hasAce(): Boolean = cards.any { it.rank == Rank.ACE }

    private fun isAceExtraValue(handValue: Int) = hasAce() && handValue <= ACE_EXTRA_VALUE_THRESHOLD

    companion object {
        private const val ACE_EXTRA_VALUE_THRESHOLD = 11
        private const val ACE_EXTRA_VALUE = 10
    }
}
