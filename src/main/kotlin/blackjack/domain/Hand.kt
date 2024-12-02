package blackjack.domain

class Hand(
    cards: List<Card> = emptyList(),
) {
    val cards = mutableListOf<Card>()

    init {
        this.cards.addAll(cards)
    }

    operator fun get(index: Int): Card = cards[index]

    fun value(): Int {
        val handValue = cards.sumOf { it.rankValue }
        if (isAceEleven(handValue)) {
            return handValue + ACE_EXTRA_VALUE
        }
        return handValue
    }

    fun drawFrom(deck: Deck) {
        cards.add(deck.draw())
    }

    fun isBlackjack(): Boolean = cards.size == 2 && value() == BLACKJACK_VALUE

    fun isBusted(): Boolean = value() > BLACKJACK_VALUE

    private fun hasAce(): Boolean = cards.any { it.rank == Rank.ACE }

    private fun isAceEleven(handValue: Int) = hasAce() && handValue <= ACE_ELEVEN_THRESHOLD

    companion object {
        private const val ACE_ELEVEN_THRESHOLD = 11
        private const val ACE_EXTRA_VALUE = 10
        private const val BLACKJACK_VALUE = 21

        fun from(vararg cards: Card): Hand = Hand(cards.toList())
    }
}
