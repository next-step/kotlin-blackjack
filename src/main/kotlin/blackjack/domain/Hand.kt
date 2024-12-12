package blackjack.domain

open class Hand(
    cards: List<Card> = emptyList(),
) {
    val cards = cards.toMutableList()
    val isTwentyOne: Boolean
        get() = value() == BLACKJACK_VALUE

    constructor(vararg cards: Card) : this(cards.toList())

    operator fun get(index: Int): Card = cards[index]

    fun value(): Int {
        val handValue = cards.sumOf { it.rankValue }
        if (isAceEleven(handValue)) {
            return handValue + ACE_EXTRA_VALUE
        }
        return handValue
    }

    open fun drawFrom(deck: Deck) {
        cards.add(deck.draw())
    }

    fun isBlackjack(): Boolean = cards.size == INITIAL_HAND_SIZE && value() == BLACKJACK_VALUE

    fun isBusted(): Boolean = value() > BLACKJACK_VALUE

    fun pushes(dealerHand: DealerHand) = value() == dealerHand.value()

    fun beats(dealerHand: DealerHand) = value() > dealerHand.value()

    private fun hasAce(): Boolean = cards.any { it.rank == Rank.ACE }

    private fun isAceEleven(handValue: Int) = hasAce() && handValue <= ACE_ELEVEN_THRESHOLD

    companion object {
        private const val ACE_ELEVEN_THRESHOLD = 11
        private const val ACE_EXTRA_VALUE = 10
        const val BLACKJACK_VALUE = 21
        const val INITIAL_HAND_SIZE = 2
    }
}
