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
        return cards.sumOf { it.denomination.score }
    }

    companion object {
        private const val MINIMUM_SIZE = 2
        private const val ERROR_MINIMUM_SIZE = "Have to have at least two cards"
    }
}
