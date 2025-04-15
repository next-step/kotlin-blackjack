class Hand(private val cards: List<PlayingCard>) {
    init {
        require(cards.size >= MINIMUM_SIZE) { ERROR_MINIMUM_SIZE }
    }

    val size: Int
        get() = cards.size

    fun add(card: PlayingCard): Hand {
        return Hand(cards + card)
    }

    fun score(): Int {
        val total = cards.sumOf { it.denomination.score }
        if (cards.any { it.denomination == Denomination.ACE } && total <= 11) return total + BONUS
        return total
    }

    companion object {
        private const val BONUS = 10
        private const val MINIMUM_SIZE = 2
        private const val ERROR_MINIMUM_SIZE = "Have to have at least two cards"
    }
}
