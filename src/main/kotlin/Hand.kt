class Hand(private val cards: List<PlayingCard>) {
    init {
        require(cards.size >= MINIMUM_SIZE) { ERROR_MINIMUM_SIZE }
    }

    fun score(): Int {
        return cards.sumOf { it.denomination.score }
    }

    companion object {
        private const val MINIMUM_SIZE = 2
        private const val ERROR_MINIMUM_SIZE = "Have to have at least two cards"
    }
}
