class Hand(cards: List<PlayingCard>) {
    init {
        require(cards.size >= MINIMUM_SIZE) { ERROR_MINIMUM_SIZE }
    }

    private val _cards: MutableList<PlayingCard> = cards.toMutableList()
    val cards: List<PlayingCard>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    fun add(card: PlayingCard) {
        _cards.add(card)
    }

    fun score(): Int {
        val total = _cards.sumOf { it.denomination.score }
        if (_cards.any { it.denomination == Denomination.ACE } && total <= 11) return total + BONUS
        return total
    }

    companion object {
        private const val BONUS = 10
        private const val MINIMUM_SIZE = 2
        private const val ERROR_MINIMUM_SIZE = "Have to have at least two cards"
    }
}
